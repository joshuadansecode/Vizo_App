import React, { useState } from 'react';
import { useNavigation } from '../../context/NavigationContext';
import { toast } from 'sonner';
import { supabase } from '../../lib/supabase';

export const LoginForm: React.FC = () => {
    const { navigate } = useNavigation();
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [showPassword, setShowPassword] = useState(false);

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        if (!email.includes('@')) {
            toast.error('Oups! Adresse email invalide 😬');
            return;
        }
        if (password.length < 6) {
            toast.error('Mot de passe trop court (min. 6 caractères)');
            return;
        }

        toast.promise(
            async () => {
                const { error } = await supabase.auth.signInWithPassword({
                    email: email,
                    password: password,
                });

                if (error) throw error;

                navigate('dashboard');
                return 'Bon retour parmi nous ! 🎉';
            },
            {
                loading: 'Connexion en cours...',
                success: (msg) => msg,
                error: (err) => `Erreur : ${err.message || 'Identifiants invalides'}`,
            }
        );
    };

    return (
        <form className="space-y-6" onSubmit={handleSubmit}>
            {/* Email Input */}
            <div className="space-y-2">
                <label className="block text-slate-700 dark:text-slate-300 text-sm font-medium pl-1">Adresse Email</label>
                <div className="relative">
                    <input
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        className="w-full h-14 bg-slate-200 dark:bg-surface-dark border-none rounded-xl text-slate-900 dark:text-white px-4 placeholder:text-slate-500 focus:ring-1 focus:ring-primary/50 text-base" placeholder="exemple@email.com" type="email" />
                </div>
            </div>

            {/* Password Input */}
            <div className="space-y-2">
                <label className="block text-slate-700 dark:text-slate-300 text-sm font-medium pl-1">Mot de passe</label>
                <div className="relative">
                    <input
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        className="w-full h-14 bg-slate-200 dark:bg-surface-dark border-none rounded-xl text-slate-900 dark:text-white px-4 pr-12 placeholder:text-slate-500 focus:ring-1 focus:ring-primary/50 text-base" placeholder="••••••••" type={showPassword ? "text" : "password"} />
                    <button
                        onClick={() => setShowPassword(!showPassword)}
                        className="absolute right-4 top-1/2 -translate-y-1/2 text-slate-400 hover:text-slate-900 dark:hover:text-white transition-colors" type="button">
                        <span className="material-symbols-outlined">{showPassword ? "visibility_off" : "visibility"}</span>
                    </button>
                </div>
                <div className="flex justify-end pt-1">
                    <a className="text-primary text-sm font-medium hover:opacity-80 transition-opacity" href="#">Mot de passe oublié ?</a>
                </div>
            </div>

            {/* Action Area */}
            <div className="pt-8 space-y-6">
                <button
                    type="submit"
                    className="w-full h-14 bg-primary text-slate-900 font-bold text-lg rounded-full shadow-lg shadow-primary/20 hover:scale-[1.02] active:scale-[0.98] transition-all">
                    Se connecter
                </button>
                <p className="text-center text-slate-500 dark:text-slate-400 text-sm">
                    Nouveau sur Vizo ?
                    <button type="button" onClick={() => navigate('signup')} className="text-primary font-bold ml-1 hover:underline">Créer un compte</button>
                </p>
            </div>
        </form>
    );
};
