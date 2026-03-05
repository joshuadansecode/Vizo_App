import React, { useState, useEffect } from 'react';
import { useNavigation } from '../context/NavigationContext';
import { supabase } from '../lib/supabase';
import { toast } from 'sonner';

export const CreateStatusPage: React.FC = () => {
    const [statusText, setStatusText] = useState('');
    const [templates, setTemplates] = useState<any[]>([]);
    const { navigate, goBack } = useNavigation();

    useEffect(() => {
        const fetchTemplates = async () => {
            const { data, error } = await supabase.from('status_templates').select('*');
            if (data && !error) {
                setTemplates(data);
            }
        };
        fetchTemplates();
    }, []);

    const applyTemplate = (text: string) => {
        setStatusText(text);
    };

    const handlePublish = () => {
        if (!statusText.trim()) {
            toast.error('Ton statut est vide ! Raconte-nous quelque chose ✍️');
            return;
        }

        toast.promise(new Promise(resolve => setTimeout(resolve, 1200)), {
            loading: 'Publication dans le pool Vizo...',
            success: () => {
                navigate('dashboard');
                return 'Statut publié avec succès ! 🚀';
            },
            error: 'Erreur de publication'
        });
    };

    return (
        <div className="bg-background-dark text-slate-100 min-h-[100dvh] flex flex-col font-display max-w-md mx-auto relative overflow-hidden">
            {/* Header */}
            <header className="px-6 py-6 flex items-center justify-between z-10">
                <button
                    onClick={goBack}
                    className="w-10 h-10 rounded-full bg-white/5 flex items-center justify-center hover:bg-white/10 transition-colors">
                    <span className="material-symbols-outlined">close</span>
                </button>
                <div className="flex gap-2">
                    <button className="bg-primary/20 text-primary px-4 py-2 rounded-full font-bold text-sm tracking-wide hover:bg-primary/30 transition-colors">
                        Brouillons
                    </button>
                    <button
                        onClick={handlePublish}
                        className="bg-primary text-slate-900 px-6 py-2 rounded-full font-bold text-sm tracking-wide shadow-lg shadow-primary/20 hover:scale-[1.02] active:scale-[0.98] transition-all">
                        Publier
                    </button>
                </div>
            </header>

            {/* Input Area */}
            <main className="flex-1 flex flex-col px-6">
                <textarea
                    value={statusText}
                    onChange={(e) => setStatusText(e.target.value)}
                    placeholder="Quoi de neuf aujourd'hui ?"
                    className="flex-1 w-full bg-transparent border-none outline-none resize-none text-2xl font-medium placeholder:text-muted-sky/50 leading-relaxed py-4 focus:ring-0"
                    autoFocus
                />
            </main>

            {/* Tools & Templates drawer */}
            <div className="bg-surface border-t border-white/5 rounded-t-3xl mt-auto shadow-[0_-10px_40px_rgba(0,0,0,0.5)] z-20">

                {/* Formatting Tools */}
                <div className="flex items-center justify-between px-8 py-4 border-b border-white/5">
                    <div className="flex gap-6">
                        <button className="text-muted-sky hover:text-white transition-colors">
                            <span className="material-symbols-outlined">text_format</span>
                        </button>
                        <button className="text-muted-sky hover:text-white transition-colors">
                            <span className="material-symbols-outlined">palette</span>
                        </button>
                        <button className="text-muted-sky hover:text-white transition-colors">
                            <span className="material-symbols-outlined">image</span>
                        </button>
                    </div>
                </div>

                {/* Templates List */}
                <div className="px-6 py-6">
                    <h3 className="text-muted-sky text-xs font-bold uppercase tracking-widest mb-4 px-2">Phrases d'accroche (Templates)</h3>
                    <div className="flex overflow-x-auto gap-3 pb-4 snap-x hide-scrollbar">
                        {templates.map(template => (
                            <button
                                key={template.id}
                                onClick={() => applyTemplate(template.text)}
                                className="snap-start shrink-0 w-[240px] bg-white/5 border border-white/10 p-4 rounded-2xl flex flex-col items-start gap-2 hover:bg-white/10 transition-colors text-left"
                            >
                                <span className="material-symbols-outlined text-primary">{template.icon}</span>
                                <p className="text-sm font-medium text-slate-200 line-clamp-2">{template.text}</p>
                            </button>
                        ))}
                    </div>
                </div>

                {/* Safe Area Spacer */}
                <div className="h-6"></div>
            </div>
        </div>
    );
};
