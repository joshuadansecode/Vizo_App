import React from 'react';

interface ActionCardProps {
    title: string;
    subtitle: string;
    buttonText: string;
}

export const ActionCard: React.FC<ActionCardProps> = ({ title, subtitle, buttonText }) => {
    return (
        <section>
            <div className="flex items-center justify-between rounded-xl border border-primary/20 bg-surface/50 p-5 backdrop-blur-sm">
                <div className="flex items-center gap-4">
                    <div className="flex size-12 items-center justify-center rounded-full bg-primary/20 text-primary">
                        <span className="material-symbols-outlined text-3xl">rocket_launch</span>
                    </div>
                    <div className="flex flex-col">
                        <p className="text-slate-100 text-base font-bold leading-tight">{title}</p>
                        <p className="text-slate-400 text-sm">{subtitle}</p>
                    </div>
                </div>
                <button className="flex items-center justify-center rounded-full border border-primary px-4 py-2 text-primary text-sm font-bold hover:bg-primary hover:text-slate-900 transition-all">
                    {buttonText}
                </button>
            </div>
        </section>
    );
};
