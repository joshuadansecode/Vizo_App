import React, { useState } from 'react';
import { onboardingSteps } from '../data/mockData';
import { useNavigation } from '../context/NavigationContext';

export const OnboardingPage: React.FC = () => {
    const [currentStep, setCurrentStep] = useState(0);
    const { navigate } = useNavigation();
    const stepData = onboardingSteps[currentStep];

    const handleNext = () => {
        if (currentStep < onboardingSteps.length - 1) {
            setCurrentStep(currentStep + 1);
        } else {
            navigate('signup');
        }
    };

    const handleSkip = () => {
        navigate('login');
    };

    return (
        <div className="relative flex min-h-screen w-full flex-col bg-background-light dark:bg-background-dark overflow-x-hidden max-w-md mx-auto font-display text-slate-900 dark:text-slate-100">

            {/* Top App Bar */}
            <header className="flex items-center justify-between py-6 px-6">
                <div className="w-16"></div> {/* Spacer */}
                <h2 className="text-primary text-xl font-bold tracking-[0.2em] drop-shadow-[0_0_10px_rgba(0,194,152,0.4)] uppercase">VIZO</h2>
                <div className="w-16 flex justify-end">
                    {currentStep < onboardingSteps.length - 1 ? (
                        <button onClick={handleSkip} className="text-muted-sky text-sm font-semibold tracking-wide hover:text-primary transition-colors">
                            Passer
                        </button>
                    ) : (
                        <button onClick={handleSkip} className="text-muted-sky text-sm font-semibold tracking-wide hover:text-primary transition-colors">
                            Connexion
                        </button>
                    )}
                </div>
            </header>

            {/* Dynamic Illustration Area */}
            <div className="flex-grow flex flex-col items-center justify-center p-6">
                <div className="relative w-full aspect-square max-w-[320px] mb-8 flex items-center justify-center">
                    {/* Decorative Glow completely depends on step */}
                    <div className="absolute inset-0 bg-primary/20 blur-[60px] rounded-full"></div>

                    {currentStep === 0 && (
                        <div className="relative w-full h-full bg-center bg-no-repeat bg-cover rounded-xl" style={{ backgroundImage: "url('https://lh3.googleusercontent.com/aida-public/AB6AXuCwPaZ9uzeTIXjNczT7HY8Eqd9DYocD_WocuQlnSh1xVBeAQfoaa3nLWnbP2m-wei-hhRKW9JFbFmRhPm1BUnc1FW8d5wrUJi8v0EowqPE6pg5r9S6gpP_MbaEpjzoJfRurqExBvlglt-xaHsX-9vXjNvUqct_4LcokxS6VUfn7TqqFSuvBCvBg71cPHHE9rtMrZhQS0j2RRqru4bDV7UPWUPCgTofPUjOkX6nJm6Fad_jhtKuiP_GLx5yBXbzwWt3qveW7NZWKWRI')" }}>
                        </div>
                    )}

                    {currentStep === 1 && (
                        <div className="relative z-10 w-full h-full flex items-center justify-center">
                            <div className="w-full h-full max-w-[280px] bg-slate-900/40 border border-slate-700/50 backdrop-blur-sm rounded-xl overflow-hidden shadow-2xl flex flex-col items-center justify-center p-8 gap-6 group">
                                <div className="w-24 h-24 bg-gradient-to-br from-primary to-emerald-400 rounded-full flex items-center justify-center shadow-[0_0_30px_rgba(0,194,152,0.4)]">
                                    <span className="material-symbols-outlined text-white text-5xl">local_fire_department</span>
                                </div>
                                <div className="flex flex-col gap-3 w-full">
                                    <div className="h-3 w-3/4 bg-primary/20 rounded-full"></div>
                                    <div className="h-3 w-full bg-slate-700/40 rounded-full"></div>
                                    <div className="h-3 w-5/6 bg-slate-700/40 rounded-full"></div>
                                </div>
                            </div>
                        </div>
                    )}

                    {currentStep === 2 && (
                        <div className="relative z-10 w-full h-full flex flex-col items-center justify-center">
                            <div className="bg-white/10 backdrop-blur-md rounded-xl p-6 w-full shadow-2xl relative border border-primary/20">
                                <div className="flex items-center justify-between mb-6">
                                    <div className="flex items-center gap-2">
                                        <span className="material-symbols-outlined text-primary text-3xl">visibility</span>
                                        <div>
                                            <p className="text-[10px] text-muted-sky font-medium uppercase tracking-wider">Visibilité</p>
                                            <p className="text-2xl font-bold text-slate-100">+124%</p>
                                        </div>
                                    </div>
                                    <div className="bg-primary/20 px-2 py-1 rounded-full">
                                        <p className="text-[10px] text-primary font-bold">LIVE</p>
                                    </div>
                                </div>
                                {/* Simple SVG Chart */}
                                <div className="w-full h-24 mb-4">
                                    <svg className="w-full h-full" viewBox="0 0 100 40">
                                        <path d="M0,35 Q10,32 20,25 T40,28 T60,15 T80,20 T100,5" fill="none" stroke="#00c298" strokeLinecap="round" strokeWidth="3"></path>
                                        <path d="M0,35 Q10,32 20,25 T40,28 T60,15 T80,20 T100,5 V40 H0 Z" fill="rgba(0,194,152,0.1)"></path>
                                    </svg>
                                </div>
                            </div>
                        </div>
                    )}

                    {currentStep === 3 && (
                        <>
                            <div className="absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 w-48 h-48 bg-[#FFD166]/10 blur-[40px] rounded-full"></div>
                            <div className="relative w-full h-full flex items-center justify-center bg-background-dark/40 rounded-xl border border-primary/10 shadow-2xl overflow-hidden">
                                <div className="flex flex-col items-center">
                                    <div className="relative mb-4">
                                        <span className="material-symbols-outlined text-[100px] text-muted-sky/40">account_balance_wallet</span>
                                        <div className="absolute -top-4 -right-4">
                                            <div className="bg-[#FFD166] p-3 rounded-full shadow-[0_0_20px_rgba(255,209,102,0.6)] animate-pulse">
                                                <span className="material-symbols-outlined text-background-dark font-bold text-3xl">add</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </>
                    )}
                </div>

                {/* Text Content */}
                <div className="text-center px-4 w-full">
                    <h1 className="text-slate-900 dark:text-white text-3xl font-bold leading-tight mb-4">
                        {stepData.title}
                    </h1>
                    <p className="text-slate-600 dark:text-muted-sky text-base leading-relaxed max-w-xs mx-auto">
                        {stepData.description}
                    </p>
                </div>
            </div>

            {/* Footer Action Area */}
            <footer className="mt-auto px-6 pt-6 pb-8">
                {/* Pagination Dots */}
                <div className="flex items-center justify-center gap-2.5 mb-8">
                    {onboardingSteps.map((_, index) => (
                        <div
                            key={index}
                            className={`rounded-full transition-all duration-300 ${currentStep === index
                                ? 'h-2 w-8 bg-primary shadow-[0_0_8px_rgba(0,194,152,0.5)]'
                                : 'h-2 w-2 bg-primary/20'
                                }`}
                        />
                    ))}
                </div>

                {/* Primary Action */}
                <button
                    onClick={handleNext}
                    className="w-full bg-primary text-slate-900 font-bold py-4 rounded-full text-lg shadow-lg shadow-primary/20 hover:bg-primary/90 active:scale-[0.98] transition-all"
                >
                    {stepData.buttonText}
                </button>
            </footer>
        </div>
    );
};
