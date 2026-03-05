import React from 'react';
import { AuthHeader } from '../components/layout/AuthHeader';
import { SignupForm } from '../components/auth/SignupForm';

export const SignupPage: React.FC = () => {
    return (
        <div className="relative flex h-auto min-h-screen w-full flex-col bg-background-light dark:bg-background-dark overflow-x-hidden font-display">
            <AuthHeader />

            <main className="flex-1 flex flex-col max-w-md mx-auto w-full relative z-10 pb-8">
                {/* Header Content */}
                <div className="px-6 pt-8 pb-4">
                    <h1 className="text-slate-900 dark:text-white tracking-tight text-[32px] font-bold leading-tight">Créer mon compte</h1>
                    <p className="text-slate-600 dark:text-muted-sky text-base font-normal leading-normal mt-2">Saisis ton numéro WhatsApp pour commencer.</p>
                </div>

                <SignupForm />
            </main>

            {/* Decorative Spacer for iOS home indicator could go here */}
        </div>
    );
};
