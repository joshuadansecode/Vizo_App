import React from 'react';

interface HeroCardProps {
    connections: number;
    timeRemaining: string;
    isLive: boolean;
}

export const HeroCard: React.FC<HeroCardProps> = ({ connections, timeRemaining, isLive }) => {
    return (
        <section className="w-full">
            <div className="relative overflow-hidden rounded-xl bg-surface p-6 shadow-2xl">
                <div className="absolute -right-10 -top-10 h-32 w-32 bg-primary/20 blur-3xl rounded-full"></div>
                <div className="relative z-10 flex flex-col gap-4">
                    <div className="flex items-center justify-between">
                        <span className="text-slate-400 text-sm font-medium">État de ton réseau</span>
                        {isLive && (
                            <span className="flex items-center gap-1 rounded-full bg-primary/10 px-2 py-1 text-[10px] font-bold text-primary uppercase tracking-tighter">
                                <span className="size-1.5 rounded-full bg-primary animate-pulse"></span>
                                Live Now
                            </span>
                        )}
                    </div>
                    <div className="flex items-baseline gap-2">
                        <h1 className="text-6xl font-black text-primary tracking-tighter">{connections}</h1>
                        <span className="text-slate-400 text-lg font-medium">Connexions</span>
                    </div>
                    <div className="flex items-center gap-2 rounded-lg bg-black/20 p-3">
                        <span className="material-symbols-outlined text-accent text-xl">schedule</span>
                        <div className="flex flex-col">
                            <p className="text-accent text-sm font-bold leading-none">Temps restant : {timeRemaining}</p>
                            <p className="text-slate-500 text-[10px] mt-1 italic">Dépêche-toi, le boost se termine bientôt !</p>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    );
};
