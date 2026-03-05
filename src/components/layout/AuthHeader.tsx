import React from 'react';
import { useNavigation } from '../../context/NavigationContext';

interface AuthHeaderProps {
    title?: string;
}

export const AuthHeader: React.FC<AuthHeaderProps> = ({ title = 'VIZO' }) => {
    const { goBack } = useNavigation();

    return (
        <header className="flex items-center justify-between px-6 py-4 sticky top-0 z-10 bg-background-light/80 dark:bg-background-dark/80 backdrop-blur-md">
            <button
                onClick={goBack}
                className="flex items-center justify-center w-10 h-10 rounded-full hover:bg-white/5 transition-colors">
                <span className="material-symbols-outlined text-slate-900 dark:text-white text-2xl">arrow_back</span>
            </button>
            <div className="flex-1 flex justify-center pr-10">
                <span className="text-primary font-bold tracking-[0.2em] text-xl">{title}</span>
            </div>
        </header>
    );
};
