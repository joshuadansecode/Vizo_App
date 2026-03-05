import React from 'react';
import { navItems } from '../../data/mockData';
import { useNavigation, type Page } from '../../context/NavigationContext';

export const BottomNav: React.FC = () => {
    const { navigate, currentPage } = useNavigation();

    return (
        <nav className="fixed bottom-0 left-0 right-0 z-50 glass-effect border-t border-white/5 px-4 pb-8 pt-3 bg-surface/80 backdrop-blur-xl">
            <div className="mx-auto flex max-w-md items-center justify-between gap-1">
                {navItems.map((item) => {
                    if (item.isAction) {
                        return (
                            <div key={item.id} className="flex flex-1 justify-center -mt-8">
                                <button
                                    onClick={() => navigate(item.id as Page)}
                                    className="flex size-14 items-center justify-center rounded-full bg-primary text-background-dark shadow-lg shadow-primary/20 border-4 border-background-dark hover:scale-105 active:scale-95 transition-transform"
                                >
                                    <span className="material-symbols-outlined text-3xl font-bold">{item.icon}</span>
                                </button>
                            </div>
                        );
                    }

                    return (
                        <button
                            key={item.id}
                            onClick={() => navigate(item.id === 'home' ? 'dashboard' : item.id as Page)}
                            className={`flex flex-1 flex-col items-center gap-1 hover:text-white transition-colors ${item.isActive || currentPage === item.id || (currentPage === 'dashboard' && item.id === 'home') ? 'text-primary' : 'text-slate-500'}`}
                        >
                            <span className={`material-symbols-outlined ${item.isActive || currentPage === item.id || (currentPage === 'dashboard' && item.id === 'home') ? 'fill-1' : ''}`}>{item.icon}</span>
                            <span className="text-[10px] font-medium">{item.label}</span>
                        </button>
                    );
                })}
            </div>
        </nav>
    );
};
