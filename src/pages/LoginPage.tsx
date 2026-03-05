import React from 'react';
import { AuthHeader } from '../components/layout/AuthHeader';
import { LoginForm } from '../components/auth/LoginForm';

export const LoginPage: React.FC = () => {
    return (
        <div className="min-h-screen flex flex-col bg-background-light dark:bg-background-dark text-slate-900 dark:text-slate-100 font-display">
            <AuthHeader />

            <main className="flex-1 flex flex-col px-6 pt-10 max-w-md mx-auto w-full relative z-10">
                {/* Title Area */}
                <div className="mb-10">
                    <h1 className="text-slate-900 dark:text-white text-4xl font-bold mb-3">Te revoilà !</h1>
                    <p className="text-slate-600 dark:text-muted-sky text-lg">Connecte-toi pour booster tes vues.</p>
                </div>

                <LoginForm />
            </main>

            {/* Aesthetic Background Elements (Abstract) */}
            <div className="fixed -bottom-20 -left-20 w-64 h-64 bg-primary/10 rounded-full blur-[100px] pointer-events-none"></div>
            <div className="fixed -top-20 -right-20 w-64 h-64 bg-primary/5 rounded-full blur-[80px] pointer-events-none"></div>

            {/* Decorative Image for Context */}
            <div className="hidden md:block fixed right-10 bottom-10 w-32 h-32 opacity-20 pointer-events-none">
                <div className="w-full h-full bg-gradient-to-tr from-primary to-transparent rounded-2xl rotate-12"></div>
            </div>
        </div>
    );
};
