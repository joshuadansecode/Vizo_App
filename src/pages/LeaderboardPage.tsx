import React, { useState } from 'react';
import { BottomNav } from '../components/layout/BottomNav';
import { leaderboardData } from '../data/mockData';

export const LeaderboardPage: React.FC = () => {
    const [activeTab, setActiveTab] = useState('points');

    return (
        <div className="bg-background-light dark:bg-background-dark text-slate-900 dark:text-slate-100 min-h-[100dvh] pb-20 flex flex-col font-display max-w-md mx-auto relative overflow-x-hidden">
            {/* Header Section */}
            <header className="sticky top-0 z-20 bg-background-dark/80 backdrop-blur-md px-6 pt-12 pb-4">
                <div className="flex items-center justify-between mb-6">
                    <button className="text-slate-100 hover:text-primary transition-colors">
                        <span className="material-symbols-outlined">arrow_back_ios</span>
                    </button>
                    <h1 className="text-xl font-bold text-white uppercase tracking-widest">Top 50</h1>
                    <button className="text-slate-100 hover:text-primary transition-colors">
                        <span className="material-symbols-outlined">share</span>
                    </button>
                </div>

                {/* Tab Selector */}
                <div className="flex p-1 bg-surface rounded-full gap-1">
                    <button
                        onClick={() => setActiveTab('points')}
                        className={`flex-1 py-2 px-4 rounded-full font-bold text-sm transition-all ${activeTab === 'points' ? 'bg-primary text-background-dark' : 'text-muted-sky hover:text-white'}`}
                    >Points</button>
                    <button
                        onClick={() => setActiveTab('parrainages')}
                        className={`flex-1 py-2 px-4 rounded-full font-bold text-sm transition-all ${activeTab === 'parrainages' ? 'bg-primary text-background-dark' : 'text-muted-sky hover:text-white'}`}
                    >Parrainages</button>
                    <button
                        onClick={() => setActiveTab('visibilite')}
                        className={`flex-1 py-2 px-4 rounded-full font-bold text-sm transition-all ${activeTab === 'visibilite' ? 'bg-primary text-background-dark' : 'text-muted-sky hover:text-white'}`}
                    >Visibilité</button>
                </div>
            </header>

            {/* Podium Section */}
            <div className="px-6 py-8 flex items-end justify-center gap-2 mb-4">
                {/* 2nd Place */}
                <div className="flex flex-col items-center">
                    <div className="relative mb-2">
                        <div className="w-16 h-16 rounded-full border-4 border-slate-400 overflow-hidden bg-surface p-0.5">
                            <img className="w-full h-full object-cover rounded-full" src={leaderboardData.topThree[0].avatar} />
                        </div>
                        <div className="absolute -bottom-2 left-1/2 -translate-x-1/2 bg-slate-400 text-background-dark text-[10px] font-bold px-2 py-0.5 rounded-full">2</div>
                    </div>
                    <p className="text-white font-semibold text-sm">{leaderboardData.topThree[0].name}</p>
                    <p className="text-muted-sky text-xs font-medium">{leaderboardData.topThree[0].score}</p>
                    <div className="w-20 h-24 bg-surface/60 rounded-t-xl mt-4 border-t-2 border-slate-400/30"></div>
                </div>

                {/* 1st Place */}
                <div className="flex flex-col items-center -translate-y-4">
                    <span className="material-symbols-outlined text-[#FFD166] text-3xl mb-1 drop-shadow-[0_0_10px_rgba(255,209,102,0.5)]">workspace_premium</span>
                    <div className="relative mb-2">
                        <div className="w-24 h-24 rounded-full border-4 border-[#FFD166] overflow-hidden bg-surface shadow-[0_0_25px_rgba(255,209,102,0.2)] p-1">
                            <img className="w-full h-full object-cover rounded-full" src={leaderboardData.topThree[1].avatar} />
                        </div>
                        <div className="absolute -bottom-2 left-1/2 -translate-x-1/2 bg-[#FFD166] text-background-dark text-xs font-bold px-3 py-0.5 rounded-full">1</div>
                    </div>
                    <p className="text-white font-bold text-lg">{leaderboardData.topThree[1].name}</p>
                    <p className="text-primary font-bold text-sm">{leaderboardData.topThree[1].score}</p>
                    <div className="w-24 h-32 bg-primary/20 rounded-t-xl mt-4 border-t-2 border-primary/50 relative overflow-hidden">
                        <div className="absolute inset-0 bg-gradient-to-t from-primary/10 to-transparent"></div>
                    </div>
                </div>

                {/* 3rd Place */}
                <div className="flex flex-col items-center">
                    <div className="relative mb-2">
                        <div className="w-16 h-16 rounded-full border-4 border-orange-400 overflow-hidden bg-surface p-0.5">
                            <img className="w-full h-full object-cover rounded-full" src={leaderboardData.topThree[2].avatar} />
                        </div>
                        <div className="absolute -bottom-2 left-1/2 -translate-x-1/2 bg-orange-400 text-background-dark text-[10px] font-bold px-2 py-0.5 rounded-full">3</div>
                    </div>
                    <p className="text-white font-semibold text-sm">{leaderboardData.topThree[2].name}</p>
                    <p className="text-muted-sky text-xs font-medium">{leaderboardData.topThree[2].score}</p>
                    <div className="w-20 h-20 bg-surface/60 rounded-t-xl mt-4 border-t-2 border-orange-400/30"></div>
                </div>
            </div>

            {/* Leaderboard List */}
            <main className="flex-1 px-4 pb-24">
                <div className="space-y-3">
                    {leaderboardData.others.map((user) => (
                        <div key={user.rank} className="flex items-center bg-surface p-4 rounded-xl border border-white/5 hover:bg-white/5 transition-colors cursor-pointer">
                            <span className="w-8 font-bold text-muted-sky text-sm">{user.rank}</span>
                            <div className="flex flex-1 items-center gap-3">
                                <div className="w-10 h-10 rounded-full bg-slate-700 overflow-hidden">
                                    <img className="w-full h-full object-cover" src={user.avatar} />
                                </div>
                                <p className="text-white font-medium">{user.name}</p>
                            </div>
                            <p className="text-primary font-bold">{user.score}</p>
                        </div>
                    ))}

                    {/* Placeholder for more ranks */}
                    <div className="py-4 text-center">
                        <div className="inline-block w-1.5 h-1.5 rounded-full bg-muted-sky/40 mx-0.5"></div>
                        <div className="inline-block w-1.5 h-1.5 rounded-full bg-muted-sky/40 mx-0.5"></div>
                        <div className="inline-block w-1.5 h-1.5 rounded-full bg-muted-sky/40 mx-0.5"></div>
                    </div>
                </div>
            </main>

            {/* Bottom Highlight Sticky Bar */}
            <div className="fixed bottom-[88px] left-0 right-0 px-4 z-10 pointer-events-none max-w-md mx-auto">
                <div className="bg-surface/95 backdrop-blur-md border-2 border-primary p-4 rounded-xl shadow-2xl flex items-center justify-between pointer-events-auto">
                    <div className="flex items-center gap-3">
                        <span className="text-lg">🎯</span>
                        <div>
                            <p className="text-white font-bold text-sm">{leaderboardData.currentUser.rank}ème - {leaderboardData.currentUser.name}</p>
                            <p className="text-muted-sky text-xs uppercase tracking-tighter">Votre Rang</p>
                        </div>
                    </div>
                    <div className="text-right">
                        <p className="text-primary font-black text-lg">{leaderboardData.currentUser.score}</p>
                    </div>
                </div>
            </div>

            <BottomNav />
        </div>
    );
};
