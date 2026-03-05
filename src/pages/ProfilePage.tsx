import React, { useEffect, useState } from 'react';
import { BottomNav } from '../components/layout/BottomNav';
import { useNavigation } from '../context/NavigationContext';
import { supabase } from '../lib/supabase';
import { toast } from 'sonner';
import { profileData as mockProfileData } from '../data/mockData';

export const ProfilePage: React.FC = () => {
    const { session, navigate } = useNavigation();
    const [profile, setProfile] = useState<any>(null);
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        const fetchProfile = async () => {
            if (!session?.user) return;

            try {
                const { data, error } = await supabase
                    .from('profiles')
                    .select('*')
                    .eq('id', session.user.id)
                    .single();

                if (error) throw error;
                setProfile(data);
            } catch (err: any) {
                console.error('Error fetching profile:', err.message);
                toast.error('Erreur lors du chargement du profil');
            } finally {
                setIsLoading(false);
            }
        };

        fetchProfile();
    }, [session]);

    const handleSignOut = async () => {
        const { error } = await supabase.auth.signOut();
        if (error) {
            toast.error('Erreur de déconnexion');
        } else {
            toast.success('À bientôt ! 👋');
            navigate('onboarding');
        }
    };

    const displayData = profile ? {
        name: profile.full_name || 'Utilisateur',
        phone: profile.phone_number || session?.user?.phone || '',
        role: profile.role || 'Ambassadeur',
        isSubscriptionActive: profile.is_subscription_active || false,
        avatarUrl: profile.avatar_url || mockProfileData.user.avatarUrl,
        balance: `${profile.balance_fcfa || 0} FCFA`
    } : { ...mockProfileData.user, balance: mockProfileData.wallet.balance };
    return (
        <div className="bg-background-light dark:bg-background-dark font-display text-slate-900 dark:text-slate-100 min-h-screen flex flex-col pb-24 relative overflow-x-hidden max-w-md mx-auto">
            {/* Header */}
            <header className="sticky top-0 z-50 bg-background-light/80 dark:bg-background-dark/80 backdrop-blur-md px-4 py-4 flex items-center justify-between border-b border-white/5">
                <div className="w-10"></div>
                <h1 className="text-xl font-bold text-slate-900 dark:text-white">Mon Profil</h1>
                <div className="relative w-10 flex justify-end">
                    <button className="p-2 rounded-full hover:bg-surface/50 transition-colors">
                        <span className="material-symbols-outlined text-slate-900 dark:text-white">notifications</span>
                        <span className="absolute top-2 right-2 w-2.5 h-2.5 bg-[#FFB800] rounded-full border-2 border-background-dark"></span>
                    </button>
                </div>
            </header>

            <main className="flex-1 overflow-y-auto px-4 py-6 space-y-6">

                {isLoading ? (
                    <div className="flex justify-center py-10">
                        <div className="w-8 h-8 rounded-full border-4 border-primary border-t-transparent animate-spin"></div>
                    </div>
                ) : (
                    <>
                        {/* User Info Card */}
                        <section className="bg-surface rounded-xl p-8 flex flex-col items-center text-center shadow-lg border border-white/5 relative overflow-hidden">
                            <div className="absolute top-0 left-0 w-full h-1 bg-gradient-to-r from-transparent via-primary to-transparent opacity-50"></div>

                            <div className="relative mb-4">
                                <div className="w-28 h-28 rounded-full border-4 border-primary/20 p-1">
                                    <div
                                        className="w-full h-full rounded-full bg-cover bg-center"
                                        style={{ backgroundImage: `url('${displayData.avatarUrl}')` }}
                                    ></div>
                                </div>
                                <div className="absolute -bottom-2 left-1/2 -translate-x-1/2 bg-[#FFB800] px-3 py-1 rounded-full shadow-lg">
                                    <span className="text-[10px] font-bold text-background-dark uppercase tracking-wider">{displayData.role}</span>
                                </div>
                            </div>

                            <div className="mt-4">
                                <h2 className="text-2xl font-bold text-white">{displayData.name}</h2>
                                <p className="text-[#7AA2C5] font-medium mt-1">{displayData.phone}</p>
                            </div>

                            {displayData.isSubscriptionActive && (
                                <div className="mt-4 bg-primary/10 border border-primary/20 px-4 py-1.5 rounded-full">
                                    <span className="text-primary text-sm font-semibold flex items-center gap-1.5">
                                        <span className="w-2 h-2 bg-primary rounded-full"></span>
                                        Abonnement Actif
                                    </span>
                                </div>
                            )}
                        </section>

                        {/* Wallet Card */}
                        <section className="bg-surface rounded-xl p-6 shadow-lg border border-white/5">
                            <div className="flex flex-col gap-1">
                                <p className="text-[#7AA2C5] text-sm font-medium">Mes Commissions</p>
                                <div className="flex items-end justify-between">
                                    <h3 className="text-3xl font-bold text-primary">{displayData.balance}</h3>
                                    <button className="bg-primary hover:bg-primary/90 text-background-dark px-6 py-2.5 rounded-full font-bold text-sm transition-all transform active:scale-95 shadow-[0_4px_14px_0_rgba(0,194,152,0.39)]">
                                        Retirer l'argent
                                    </button>
                                </div>
                            </div>
                        </section>

                        {/* Settings List */}
                        <section className="space-y-3">
                            <h4 className="text-[#7AA2C5] text-xs font-bold uppercase tracking-widest px-1">Paramètres du compte</h4>
                            <div className="bg-surface rounded-xl overflow-hidden border border-white/5">
                                {mockProfileData.settings.map((setting) => (
                                    <a key={setting.id} className="flex items-center justify-between p-4 hover:bg-white/5 transition-colors border-b border-white/5 group" href="#">
                                        <div className="flex items-center gap-3">
                                            <div className="w-10 h-10 rounded-lg bg-primary/10 flex items-center justify-center text-primary">
                                                <span className="material-symbols-outlined">{setting.icon}</span>
                                            </div>
                                            <span className="font-medium text-slate-100">{setting.label}</span>
                                        </div>
                                        <span className="material-symbols-outlined text-[#7AA2C5] group-hover:translate-x-1 transition-transform">chevron_right</span>
                                    </a>
                                ))}
                            </div>
                        </section>

                        {/* Logout */}
                        <div className="pt-4 pb-8 flex justify-center">
                            <button onClick={handleSignOut} className="flex items-center gap-2 text-[#F96167] font-bold hover:opacity-80 transition-opacity">
                                <span className="material-symbols-outlined">logout</span>
                                Se déconnecter
                            </button>
                        </div>
                    </>
                )}
            </main>

            <BottomNav />
        </div>
    );
};
