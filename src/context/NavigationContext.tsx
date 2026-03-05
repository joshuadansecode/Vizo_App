import React, { createContext, useContext, useState, useEffect, type ReactNode } from 'react';
import { supabase } from '../lib/supabase';
import type { Session } from '@supabase/supabase-js';

export type Page = 'dashboard' | 'login' | 'signup' | 'onboarding' | 'profile' | 'boosts' | 'leaderboard' | 'contacts' | 'create';

interface NavigationContextType {
    currentPage: Page;
    navigate: (page: Page) => void;
    goBack: () => void;
    session: Session | null;
}

const NavigationContext = createContext<NavigationContextType | undefined>(undefined);

export const NavigationProvider: React.FC<{ children: ReactNode }> = ({ children }) => {
    const [history, setHistory] = useState<Page[]>(['onboarding']);
    const [session, setSession] = useState<Session | null>(null);
    const [isInitialized, setIsInitialized] = useState(false);

    useEffect(() => {
        // Initial session fetch
        supabase.auth.getSession().then(({ data: { session } }) => {
            setSession(session);
            // Redirection logic based on initial session
            if (session) {
                setHistory(['dashboard']);
            } else {
                setHistory(['onboarding']);
            }
            setIsInitialized(true);
        });

        // Listen for auth changes
        const { data: { subscription } } = supabase.auth.onAuthStateChange((_event, session) => {
            setSession(session);
            if (session && history[history.length - 1] !== 'dashboard') {
                setHistory(prev => [...prev, 'dashboard']); // Automatically go to dashboard on login/signup
            } else if (!session) {
                setHistory(['onboarding']); // Back to start if logged out
            }
        });

        return () => subscription.unsubscribe();
    }, []);

    const currentPage = history[history.length - 1];

    const navigate = (page: Page) => {
        setHistory(prev => [...prev, page]);
    };

    const goBack = () => {
        setHistory(prev => (prev.length > 1 ? prev.slice(0, -1) : prev));
    };

    if (!isInitialized) {
        return <div className="min-h-screen bg-background flex flex-col items-center justify-center">
            <div className="w-8 h-8 rounded-full border-4 border-primary border-t-transparent animate-spin"></div>
        </div>;
    }

    return (
        <NavigationContext.Provider value={{ currentPage, navigate, goBack, session }}>
            {children}
        </NavigationContext.Provider>
    );
};

export const useNavigation = () => {
    const context = useContext(NavigationContext);
    if (context === undefined) {
        throw new Error('useNavigation must be used within a NavigationProvider');
    }
    return context;
};
