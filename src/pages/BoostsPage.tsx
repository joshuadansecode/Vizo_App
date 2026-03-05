import React from 'react';
import { boostsData } from '../data/mockData';
import { useNavigation } from '../context/NavigationContext';
import { toast } from 'sonner';

export const BoostsPage: React.FC = () => {
    const { navigate, goBack } = useNavigation();

    const handleBoost = (plan: any) => {
        toast.promise(new Promise(resolve => setTimeout(resolve, 1500)), {
            loading: `Initialisation du paiement pour le pack ${plan.duration}...`,
            success: () => {
                navigate('dashboard');
                return `Demande de Mobile Money envoyée sur ton téléphone 📱`;
            },
            error: 'Erreur'
        });
    };
    return (
        <div className="bg-background-light dark:bg-background-dark text-slate-900 dark:text-slate-100 min-h-[100dvh] font-display flex flex-col overflow-x-hidden max-w-md mx-auto">
            {/* TopAppBar */}
            <div className="flex items-center bg-transparent p-4 pb-2 justify-between sticky top-0 z-10 backdrop-blur-md">
                <button
                    onClick={goBack}
                    className="text-slate-900 dark:text-slate-100 flex size-10 shrink-0 items-center justify-center rounded-full hover:bg-black/5 dark:hover:bg-white/10 cursor-pointer transition-colors border-none outline-none">
                    <span className="material-symbols-outlined">arrow_back_ios_new</span>
                </button>
                <h2 className="text-slate-900 dark:text-slate-100 text-lg font-bold leading-tight tracking-tight flex-1 text-center pr-10">Booster ma visibilité</h2>
            </div>

            {/* Hero Section */}
            <div className="px-6 pt-8 pb-6 flex flex-col items-center text-center">
                <div className="inline-flex items-center justify-center p-3 mb-4 rounded-2xl bg-primary/10 text-primary">
                    <span className="material-symbols-outlined text-4xl">rocket_launch</span>
                </div>
                <h3 className="text-primary tracking-tight text-3xl font-extrabold leading-tight mb-2">
                    Obtiens +4 contacts par heure !
                </h3>
                <p className="text-muted-sky text-base font-medium leading-normal max-w-[280px]">
                    Propulse tes statuts au sommet du pool Vizo.
                </p>
            </div>

            {/* Pricing Cards Container */}
            <div className="flex flex-col gap-4 px-4 py-4 w-full">
                {boostsData.map((plan) => (
                    <div
                        key={plan.id}
                        className={`flex flex-col gap-4 rounded-2xl p-6 transition-all active:scale-[0.98] relative ${plan.isPopular
                            ? 'border-2 border-primary bg-surface shadow-[0_0_20px_rgba(0,194,152,0.15)]'
                            : 'border border-slate-300 dark:border-slate-700/50 bg-white dark:bg-surface'
                            }`}
                    >
                        {plan.isPopular && (
                            <div className="absolute -top-3 right-6">
                                <span className="bg-[#FFD166] text-background-dark text-[10px] font-black uppercase tracking-widest px-3 py-1 rounded-full shadow-lg">
                                    Plus Populaire
                                </span>
                            </div>
                        )}

                        <div className="flex justify-between items-start">
                            <div className="flex flex-col gap-1">
                                <h1 className={`${plan.isPopular ? 'text-primary' : 'text-slate-500 dark:text-slate-400'} text-sm font-semibold uppercase tracking-wider`}>
                                    {plan.duration}
                                </h1>
                                <p className="flex items-baseline gap-1 text-slate-900 dark:text-white">
                                    <span className="text-3xl font-bold tracking-tight">{plan.price}</span>
                                </p>
                            </div>
                            <div className={plan.isPopular ? 'text-primary' : 'text-primary/40'}>
                                <span className="material-symbols-outlined text-3xl">{plan.icon}</span>
                            </div>
                        </div>

                        <div className="flex flex-col gap-2 py-2">
                            {plan.features.map((feature, index) => (
                                <div key={index} className={`text-sm font-medium leading-normal flex items-center gap-3 ${plan.isPopular ? 'text-slate-800 dark:text-white font-semibold' : 'text-slate-600 dark:text-slate-200'}`}>
                                    <span className="material-symbols-outlined text-primary text-xl">check_circle</span>
                                    {feature}
                                </div>
                            ))}
                        </div>

                        <button
                            onClick={() => handleBoost(plan)}
                            className={`flex w-full cursor-pointer items-center justify-center overflow-hidden rounded-xl h-12 px-4 text-sm leading-normal tracking-wide transition-all active:scale-[0.98] ${plan.isPopular
                                ? 'bg-primary text-slate-900 font-black shadow-lg shadow-primary/20 hover:scale-[1.02]'
                                : 'border-2 border-primary/50 bg-transparent text-primary font-bold hover:bg-primary/10 hover:border-primary'
                                }`}>
                            <span className="truncate">{plan.isPopular ? 'Prendre le Boost' : 'Choisir ce pack'}</span>
                        </button>
                    </div>
                ))}
            </div>

            {/* Footer Section */}
            <div className="mt-auto px-6 py-8 flex flex-col items-center gap-6">
                <div className="flex flex-col items-center gap-3">
                    <div className="flex items-center gap-2 text-primary/80">
                        <span className="material-symbols-outlined text-lg">shield</span>
                        <span className="text-xs font-bold uppercase tracking-widest">Paiement sécurisé</span>
                    </div>
                    <p className="text-slate-500 dark:text-slate-400 text-xs font-medium text-center">Via Mobile Money local</p>
                </div>

                <div className="flex items-center justify-center gap-6 grayscale opacity-70">
                    <div className="h-8 w-12 bg-black/5 dark:bg-white/5 border border-slate-200 dark:border-transparent rounded-md flex items-center justify-center p-1">
                        <img alt="MTN Logo" className="h-full object-contain" src="https://lh3.googleusercontent.com/aida-public/AB6AXuD0_IfnxctpnNmnHuKo1nYpd5MA8c9X4DRDzsLSCFWjxZZG3Ews0fJKHkTX0FD-kw0hpfTWzLo7H3_4pLf6prJl11tr9ApBeY1J3LtzE7Dj21tQK_ghRhADFogUHSBQoiGH3Hi4q5OtisAGL5D7RoPqU4JZcFnGkSF0FdearFaW-e30Lealv3rDC9iMDAcYQ8_QNH4I-_7z4a5kTAPU8cUuS-h44OrDKWK8IfCeIADRscwqtlwpkQsaMadulVuS0tgskVWGM4n7moE" />
                    </div>
                    <div className="h-8 w-12 bg-black/5 dark:bg-white/5 border border-slate-200 dark:border-transparent rounded-md flex items-center justify-center p-1">
                        <img alt="Orange Logo" className="h-full object-contain" src="https://lh3.googleusercontent.com/aida-public/AB6AXuCZ2rxr8Q64w6I6kixfsA15nXeaMAVh_P8Y7KseCy26-LMbjeY6K4HPBvUPs4rGiQx_KHuQPqOacO0Zu4zT8zmZKhsdZodTKz8qtWfhTf7oQk8DZ9r7J5ouBuddBfDSCHO7VFPCPcYk7b4BAYl6J8oEoOg5UItfwpaxKbyjhhkgxheix-fltN2_Dl6zYJfSQ88rC7u8eKqEG26D2oNW_A0ezBH_r86pTJT1YI7KLvXCJsNxOmW18VS7bXB-wgv5zsCw2Ph1KJhnPc0" />
                    </div>
                    <div className="h-8 w-12 bg-black/5 dark:bg-white/5 border border-slate-200 dark:border-transparent rounded-md flex items-center justify-center p-1">
                        <div className="bg-blue-600 w-full h-full rounded flex items-center justify-center text-[8px] font-bold text-white">MOOV</div>
                    </div>
                </div>

                <p className="text-slate-500 text-[10px] text-center max-w-[240px]">
                    En continuant, vous acceptez nos conditions de vente et l'activation immédiate du boost sélectionné.
                </p>
            </div>
        </div>
    );
};
