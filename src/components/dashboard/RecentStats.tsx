import React from 'react';

interface RecentStatsProps {
    totalViews: number;
    visibilityScore: number;
}

export const RecentStats: React.FC<RecentStatsProps> = ({ totalViews, visibilityScore }) => {
    return (
        <section className="space-y-4">
            <h3 className="text-slate-100 text-lg font-bold">Ton dernier statut</h3>
            <div className="grid grid-cols-2 gap-4">
                {/* Views Card */}
                <div className="rounded-xl bg-surface p-4 flex flex-col gap-2 border border-slate-800">
                    <span className="material-symbols-outlined text-primary">visibility</span>
                    <p className="text-2xl font-bold text-primary">{totalViews}</p>
                    <p className="text-slate-400 text-xs font-medium uppercase tracking-wide">Vues totales</p>
                </div>
                {/* Score Card */}
                <div className="rounded-xl bg-surface p-4 flex items-center justify-between border border-slate-800">
                    <div className="flex flex-col gap-1">
                        <p className="text-2xl font-bold text-slate-100">{visibilityScore}<span className="text-sm text-slate-500">/100</span></p>
                        <p className="text-slate-400 text-[10px] font-medium uppercase tracking-wide">Score Visibilité</p>
                    </div>
                    <div className="relative flex size-12 items-center justify-center">
                        <svg className="size-full -rotate-90" viewBox="0 0 36 36">
                            <path className="stroke-slate-800" d="M18 2.0845 a 15.9155 15.9155 0 0 1 0 31.831 a 15.9155 15.9155 0 0 1 0 -31.831" fill="none" strokeWidth="3"></path>
                            <path className="stroke-primary" d="M18 2.0845 a 15.9155 15.9155 0 0 1 0 31.831 a 15.9155 15.9155 0 0 1 0 -31.831" fill="none" strokeDasharray={`${visibilityScore}, 100`} strokeLinecap="round" strokeWidth="3"></path>
                        </svg>
                        <span className="absolute text-[10px] font-bold text-primary">{visibilityScore}%</span>
                    </div>
                </div>
            </div>
        </section>
    );
};
