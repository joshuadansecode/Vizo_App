import React, { useState } from 'react';
import { BottomNav } from '../components/layout/BottomNav';
import { contactsData } from '../data/mockData';

export const ContactsPage: React.FC = () => {
    const [searchQuery, setSearchQuery] = useState('');
    const [activeTab, setActiveTab] = useState<'pool' | 'filleuls'>('pool');

    const filteredContacts = contactsData.filter(contact =>
        contact.name.toLowerCase().includes(searchQuery.toLowerCase()) ||
        contact.phone.includes(searchQuery)
    );

    return (
        <div className="bg-background-light dark:bg-background-dark text-slate-900 dark:text-slate-100 min-h-[100dvh] pb-20 flex flex-col font-display max-w-md mx-auto relative overflow-x-hidden">
            {/* Header Section */}
            <header className="sticky top-0 z-20 bg-background-light/80 dark:bg-background-dark/80 backdrop-blur-md px-6 pt-12 pb-4 border-b border-white/5">
                <div className="flex items-center justify-between mb-4">
                    <button className="text-slate-900 dark:text-slate-100 hover:text-primary transition-colors">
                        <span className="material-symbols-outlined">arrow_back_ios</span>
                    </button>
                    <h1 className="text-xl font-bold uppercase tracking-widest">Contacts</h1>
                    <div className="w-6"></div> {/* Spacer to center title */}
                </div>

                {/* Search Bar */}
                <div className="relative mb-4">
                    <span className="material-symbols-outlined absolute left-3 top-1/2 -translate-y-1/2 text-muted-sky">search</span>
                    <input
                        type="text"
                        placeholder="Rechercher un contact..."
                        value={searchQuery}
                        onChange={(e) => setSearchQuery(e.target.value)}
                        className="w-full bg-surface border border-white/10 rounded-full py-2.5 pl-10 pr-4 text-sm text-white placeholder-muted-sky focus:outline-none focus:border-primary focus:ring-1 focus:ring-primary transition-all"
                    />
                </div>

                {/* Tab Selector */}
                <div className="flex p-1 bg-surface rounded-full gap-1">
                    <button
                        onClick={() => setActiveTab('pool')}
                        className={`flex-1 py-1.5 px-4 rounded-full font-bold text-sm transition-all ${activeTab === 'pool' ? 'bg-primary text-background-dark shadow-md' : 'text-muted-sky hover:text-white'}`}
                    >Pool Vizo</button>
                    <button
                        onClick={() => setActiveTab('filleuls')}
                        className={`flex-1 py-1.5 px-4 rounded-full font-bold text-sm transition-all ${activeTab === 'filleuls' ? 'bg-primary text-background-dark shadow-md' : 'text-muted-sky hover:text-white'}`}
                    >Mes Filleuls (<span className="text-xs">0</span>)</button>
                </div>
            </header>

            {/* Action Banner */}
            <div className="px-6 py-4">
                <button className="w-full bg-[#FFD166]/10 border border-[#FFD166]/20 py-3 px-4 rounded-xl flex items-center justify-center gap-2 hover:bg-[#FFD166]/20 transition-colors active:scale-95">
                    <span className="material-symbols-outlined text-[#FFD166]">person_add</span>
                    <span className="text-white font-bold text-sm">Synchroniser mes contacts</span>
                </button>
            </div>

            {/* Contacts List */}
            <main className="flex-1 px-4 pb-24">
                {activeTab === 'pool' ? (
                    <div className="space-y-3">
                        <p className="text-muted-sky text-xs font-bold uppercase tracking-widest mb-2 px-2">Contacts suggérés ({filteredContacts.length})</p>

                        {filteredContacts.map(contact => (
                            <div key={contact.id} className="flex items-center justify-between bg-surface p-3 rounded-xl border border-white/5 hover:border-white/10 transition-colors">
                                <div className="flex items-center gap-3">
                                    <div className="w-10 h-10 rounded-full bg-slate-700 overflow-hidden relative">
                                        <img src={contact.avatar} alt={contact.name} className="w-full h-full object-cover" />
                                        {contact.isSubscribed && (
                                            <div className="absolute bottom-0 right-0 w-2.5 h-2.5 bg-primary rounded-full border border-surface"></div>
                                        )}
                                    </div>
                                    <div>
                                        <p className="text-white font-semibold text-sm">{contact.name}</p>
                                        <p className="text-muted-sky text-xs">{contact.phone}</p>
                                    </div>
                                </div>
                                <button className="w-8 h-8 rounded-full bg-primary/10 flex items-center justify-center text-primary hover:bg-primary/20 transition-colors active:scale-90">
                                    <span className="material-symbols-outlined text-[18px]">add</span>
                                </button>
                            </div>
                        ))}

                        {filteredContacts.length === 0 && (
                            <div className="py-12 flex flex-col items-center justify-center text-center px-4">
                                <span className="material-symbols-outlined text-4xl text-muted-sky/50 mb-3">search_off</span>
                                <p className="text-muted-sky font-medium">Aucun contact trouvé.</p>
                            </div>
                        )}
                    </div>
                ) : (
                    <div className="py-12 flex flex-col items-center justify-center text-center px-4">
                        <div className="w-16 h-16 bg-surface rounded-full flex items-center justify-center mb-4 border border-white/5">
                            <span className="material-symbols-outlined text-3xl text-primary/50">groups</span>
                        </div>
                        <h3 className="text-white font-bold mb-2">Pas encore de filleuls</h3>
                        <p className="text-muted-sky text-sm mb-6">Partage ton code pour gagner de l'argent et agrandir ton réseau.</p>
                        <button className="bg-primary hover:bg-primary/90 text-background-dark font-bold py-2 px-6 rounded-full text-sm transition-all shadow-[0_4px_10px_rgba(0,194,152,0.3)]">
                            Afficher mon code
                        </button>
                    </div>
                )}
            </main>

            <BottomNav />
        </div>
    );
};
