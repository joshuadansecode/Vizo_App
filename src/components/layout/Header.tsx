import React from 'react';

interface HeaderProps {
    firstName: string;
    statusText: string;
    avatarUrl: string;
}

export const Header: React.FC<HeaderProps> = ({ firstName, statusText, avatarUrl }) => {
    return (
        <header className="flex items-center bg-transparent p-6 justify-between">
            <div className="flex items-center gap-3">
                <div className="size-12 shrink-0 overflow-hidden rounded-full border-2 border-primary/30">
                    <img alt="User Profile" className="h-full w-full object-cover" src={avatarUrl} />
                </div>
                <div>
                    <h2 className="text-slate-100 text-lg font-bold leading-tight">Salut, {firstName} 👋</h2>
                    <p className="text-primary text-xs font-medium uppercase tracking-wider">{statusText}</p>
                </div>
            </div>
            <button className="flex size-10 items-center justify-center rounded-full bg-surface text-slate-100 hover:bg-surface/80 transition-colors">
                <span className="material-symbols-outlined">settings</span>
            </button>
        </header>
    );
};
