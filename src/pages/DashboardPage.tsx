import React from 'react';
import { Header } from '../components/layout/Header';
import { BottomNav } from '../components/layout/BottomNav';
import { HeroCard } from '../components/dashboard/HeroCard';
import { ActionCard } from '../components/dashboard/ActionCard';
import { RecentStats } from '../components/dashboard/RecentStats';
import { dashboardData } from '../data/mockData';

export const DashboardPage: React.FC = () => {
    return (
        <div className="relative flex min-h-screen w-full flex-col overflow-x-hidden pb-24 bg-background-dark text-slate-100">
            <Header
                firstName={dashboardData.user.firstName}
                statusText={dashboardData.user.statusText}
                avatarUrl={dashboardData.user.avatarUrl}
            />
            <main className="px-6 space-y-6 flex-1">
                <HeroCard
                    connections={dashboardData.networkStatus.connections}
                    timeRemaining={dashboardData.networkStatus.timeRemaining}
                    isLive={dashboardData.networkStatus.isLive}
                />
                <ActionCard
                    title={dashboardData.boost.title}
                    subtitle={dashboardData.boost.subtitle}
                    buttonText={dashboardData.boost.buttonText}
                />
                <RecentStats
                    totalViews={dashboardData.recentStats.totalViews}
                    visibilityScore={dashboardData.recentStats.visibilityScore}
                />
                <section className="pb-10">
                    <div className="rounded-xl border border-dashed border-slate-800 p-8 flex flex-col items-center justify-center text-center opacity-50">
                        <span className="material-symbols-outlined text-4xl mb-2 text-slate-600">bar_chart</span>
                        <p className="text-slate-500 text-sm">Plus de statistiques arrivent bientôt...</p>
                    </div>
                </section>
            </main>
            <BottomNav />
        </div>
    );
};
