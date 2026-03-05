import React, { useState } from 'react';
import { useNavigation } from '../../context/NavigationContext';
import { toast } from 'sonner';
import { supabase } from '../../lib/supabase';

export const SignupForm: React.FC = () => {
    const { navigate } = useNavigation();
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [showPassword, setShowPassword] = useState(false);
    const [optIn, setOptIn] = useState(false);

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        if (!name.trim()) return toast.error('Veuillez entrer votre prénom ou pseudo');
        if (!email.includes('@')) return toast.error('Email invalide');
        if (password.length < 6) return toast.error('Mot de passe trop court (min. 6 caractères)');
        if (!optIn) return toast.error('Vous devez accepter de participer au pool de contacts 🤝');

        toast.promise(
            async () => {
                const { data, error } = await supabase.auth.signUp({
                    email: email,
                    password: password,
                    options: {
                        data: {
                            full_name: name,
                        }
                    }
                });

                if (error) throw error;
                if (!data.user) throw new Error("Erreur de création d'utilisateur");

                // Note: En mode production, OTP sera nécessaire.
                // Pour le MVP local, on simule une connexion ou on gère l'état
                navigate('dashboard');
                return 'Bienvenue sur Vizo ! 🚀';
            },
            {
                loading: 'Création du compte...',
                success: (msg) => msg,
                error: (err) => `Erreur : ${err.message || 'Création échouée'}`,
            }
        );
    };

    return (
        <form className="flex flex-col gap-5 px-6 py-4 flex-1" onSubmit={handleSubmit}>
            {/* Prénom */}
            <div className="flex flex-col gap-2">
                <label className="text-slate-700 dark:text-muted-sky text-sm font-medium ml-1">Prénom ou Pseudo</label>
                <div className="relative">
                    <input
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        className="form-input flex w-full rounded-xl text-slate-900 dark:text-white focus:ring-2 focus:ring-primary border-none bg-slate-200 dark:bg-surface-dark h-14 placeholder:text-slate-400 dark:placeholder:text-slate-500 p-4 text-base font-normal" placeholder="Ex: Jean" type="text" />
                </div>
            </div>

            {/* Email */}
            <div className="flex flex-col gap-2">
                <label className="text-slate-700 dark:text-muted-sky text-sm font-medium ml-1">Adresse Email</label>
                <div className="relative">
                    <input
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        className="form-input flex w-full rounded-xl text-slate-900 dark:text-white focus:ring-2 focus:ring-primary border-none bg-slate-200 dark:bg-surface-dark h-14 placeholder:text-slate-400 dark:placeholder:text-slate-500 p-4 text-base font-normal" placeholder="exemple@email.com" type="email" />
                </div>
            </div>

            {/* Password */}
            <div className="flex flex-col gap-2">
                <label className="text-slate-700 dark:text-muted-sky text-sm font-medium ml-1">Mot de passe</label>
                <div className="relative">
                    <input
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        className="form-input flex w-full rounded-xl text-slate-900 dark:text-white focus:ring-2 focus:ring-primary border-none bg-slate-200 dark:bg-surface-dark h-14 placeholder:text-slate-400 dark:placeholder:text-slate-500 p-4 text-base font-normal" placeholder="••••••••" type={showPassword ? "text" : "password"} />
                    <button
                        onClick={() => setShowPassword(!showPassword)}
                        className="absolute right-4 top-1/2 -translate-y-1/2 text-slate-400 hover:text-slate-900 dark:hover:text-white transition-colors" type="button">
                        <span className="material-symbols-outlined">{showPassword ? "visibility_off" : "visibility"}</span>
                    </button>
                </div>
            </div>

            {/* Opt-in Checkbox */}
            <label className="flex items-start gap-3 mt-2 cursor-pointer group">
                <div className="relative flex items-center mt-1">
                    <input
                        checked={optIn}
                        onChange={(e) => setOptIn(e.target.checked)}
                        className="peer h-5 w-5 rounded border-none bg-slate-200 dark:bg-surface-dark text-primary focus:ring-offset-0 focus:ring-primary checked:bg-primary" type="checkbox" />
                    <span className="material-symbols-outlined absolute text-white text-xs opacity-0 peer-checked:opacity-100 pointer-events-none left-1/2 -translate-x-1/2 font-bold">check</span>
                </div>
                <span className="text-slate-600 dark:text-muted-sky text-sm leading-tight">
                    J'accepte de partager mon numéro avec la communauté Vizo pour participer au pool de contacts.
                </span>
            </label>

            {/* Action Area */}
            <div className="mt-8 flex flex-col gap-4">
                <button
                    type="submit"
                    className="w-full bg-primary hover:bg-primary/90 text-background-dark font-bold text-lg h-14 rounded-full transition-all active:scale-[0.98] shadow-lg shadow-primary/20">
                    S'inscrire
                </button>
                <button
                    type="button"
                    onClick={() => navigate('login')}
                    className="text-primary text-center font-semibold text-base py-2 hover:underline decoration-primary underline-offset-4">
                    Déjà un compte ? Se connecter
                </button>
            </div>
        </form>
    );
};
