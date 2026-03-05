export const dashboardData = {
    user: {
        firstName: "Alex",
        statusText: "État Essai / 72h magiques",
        avatarUrl: "https://lh3.googleusercontent.com/aida-public/AB6AXuCJ0eGDkcxNh8pK-qrPT0We5fKpXSoTiDgwl9BvpVmcMFrRNW6a1BDbTkVTCU2uUt-uTCpVHUZI9wLKIFud9kTAnY4g0a6aQo7wHt1Nd_LeNHNw0t8KY5lkJLsAhcX4E1WoPjwplkYjBGe0EIxeCbN5cbjsJlStyWQA-oPFVXHOx-Y6PL23tjLHY-J8WD-WxrYMJwQBpyVYV68YT0NbjQQ44vbj8bXVefvdcIqf4Xc9DVQ5lXpwMlKwxfVgqiNub5xUTxGO4EqXJ_M"
    },
    networkStatus: {
        connections: 120,
        timeRemaining: "48h 12m",
        isLive: true
    },
    boost: {
        title: "Boost ta visibilité",
        subtitle: "Atteins 500+ prospects",
        buttonText: "Voir les boosts"
    },
    recentStats: {
        title: "Ton dernier statut",
        totalViews: 342,
        visibilityScore: 85
    }
};

export const navItems = [
    { id: 'home', icon: 'home', label: 'Accueil', isActive: true },
    { id: 'contacts', icon: 'group', label: 'Contacts', isActive: false },
    { id: 'create', icon: 'add', label: 'Créer', isAction: true },
    { id: 'leaderboard', icon: 'leaderboard', label: 'Classement', isActive: false },
    { id: 'profile', icon: 'person', label: 'Profil', isActive: false },
];

export const onboardingSteps = [
    {
        id: 'network',
        title: 'Construis ton réseau WhatsApp',
        description: 'Rejoins une communauté de jeunes qui font exploser les vues sur leurs statuts tous les jours.',
        buttonText: 'Continuer'
    },
    {
        id: 'viral',
        title: 'Crée des statuts qui cartonnent',
        description: 'Utilise nos templates et nos phrases d\'accroche pour capter l\'attention de tes contacts.',
        buttonText: 'Continuer'
    },
    {
        id: 'performance',
        title: 'Analyse tes performances',
        description: 'Suis ton score de visibilité en temps réel et découvre tes contenus les plus performants.',
        buttonText: 'Continuer'
    },
    {
        id: 'earn',
        title: 'Gagne de l\'argent réel',
        description: 'Parraine tes amis, fais grandir la communauté, et encaisse tes commissions directement sur ton Mobile Money.',
        buttonText: 'Créer mon compte'
    }
];

export const profileData = {
    user: {
        name: "Koffi L.",
        phone: "+229 97 00 00 00",
        role: "Ambassadeur",
        isSubscriptionActive: true,
        avatarUrl: "https://lh3.googleusercontent.com/aida-public/AB6AXuCgyZ_HYmUz2f1svNcvZ3dUGLYGtO_EUgd4r0iPcfs-oXklHAFBjC4r-PNgaoTiF7j1HF4biXqVWFR1-_aXe0mznWIOuUViVKbQNyQMwH7ZEjKLqqlaaBdzAzeCX3sqX6GoJvJrwSwL4pZJm7YBZb_AR2ZIT3Si-bUBOCpw5zWRE4dn984bvJxGnpRQIUsOdS0NCqQSkMIZQyUGibVpYYRH93fmJt6xWSmTFIKb6rV6N3z-lrBSOxRjn0FuO_eoD1T5lX8MNz8WWA"
    },
    wallet: {
        balance: "14 000 FCFA"
    },
    settings: [
        { id: 'subscription', icon: 'card_membership', label: 'Gérer mon abonnement' },
        { id: 'referral', icon: 'share', label: 'Mon code de parrainage' },
        { id: 'stats', icon: 'monitoring', label: 'Historique des statistiques' },
        { id: 'support', icon: 'help', label: 'Aide & Support' }
    ]
};

export const boostsData = [
    {
        id: '1-day',
        duration: '1 Jour',
        price: '150 FCFA',
        icon: 'bolt',
        features: ['Jusqu\'à 96 contacts'],
        isPopular: false
    },
    {
        id: '3-days',
        duration: '3 Jours',
        price: '400 FCFA',
        icon: 'auto_awesome',
        features: ['Jusqu\'à 288 contacts', 'Visibilité prioritaire'],
        isPopular: true
    },
    {
        id: '7-days',
        duration: '7 Jours',
        price: '700 FCFA',
        icon: 'diamond',
        features: ['Jusqu\'à 672 contacts'],
        isPopular: false
    }
];

export const leaderboardData = {
    topThree: [
        { rank: 2, name: 'Sarah', score: '1200 pts', avatar: 'https://lh3.googleusercontent.com/aida-public/AB6AXuAnk6sH6OsFCh425HnX3zujRUCnpOk3UJEwAZYSfkMjfCUWXc_fPECri3QjXoXDwRVih5aLZWHUd9-6yLH4wDipu5e2x3Hg8lQxnmvMKZoEdZReVPzH9Bc-PAqFNI9iomg4UpGmUJNj5hgx9Cc4UhV-gcm-DYtGY6_VaRYqERr2H6CXQteBkR15urPLW8jLruBZLVxfTCE2dY49dYOc3Sk2fIFfa6C0q8SCWCk4_TJWR5lAmx738fDU8L5OIUx2_Iq-TXAKRohLxoI', color: 'slate' },
        { rank: 1, name: 'Amadou', score: '1500 pts', avatar: 'https://lh3.googleusercontent.com/aida-public/AB6AXuD1rErzDAgZWQWI5otuwNi9GN0OQesbDCJNwpla2l7EOp2y0wDe-P1ftllqgVygWfWYXen6EcGYa7ayY6MbHkxO8j8aqgXZbYN-9IqPcTqOGZu62DE2AmNX3lLy3pO1UJaDiEZgQn1_M8C1tvcMg9J73YYVTQCd4vW8hNE2pd_5oduRDlXdSus-wY_uX8SkKV0pWzMw0YPPP6N9F7MbIOpP0vdUFqj6RX3w8Ydzn2yLcL4nUjIL7anK5Lm3R2j42M_e1CEYYYzcsDE', color: 'gold' },
        { rank: 3, name: 'Marc', score: '1000 pts', avatar: 'https://lh3.googleusercontent.com/aida-public/AB6AXuCrmLlOR5RztfCOcAEqKna0fCxXH2eoGa_ewuGVJih7ztNTvn4Ix0Hjg5oPGCzFDAN_6Zv4xnqLU5OxW5ieK_EqjCea1t2XVg-QxRz-7HXplDtwNu9pNFdEzNV1N8Xd9uUTIWUFtoWUGfZKMJHSYVm7IqxKZ8Fz-rA-lYfv9r6Nr9RoaTcrw52ElvV3QKwQuAR31A6M3pRy1Emp5ImrNjN35r8lI_AdFPdyomM1rtOkKQuNpbNLNiK88P-Woor4Z6O0Is5P3CV2EJE', color: 'orange' }
    ],
    others: [
        { rank: 4, name: 'Elena Petrova', score: '945 pts', avatar: 'https://lh3.googleusercontent.com/aida-public/AB6AXuAKlNqnNvf9Qj0PlgWVmVUPWzbH_CGhqNhCbuI9pGIM3tMVtTyIsvY8U2KyRVCzvERdEZOj1mA47kRcxxIjGnd_CIPt64994Y3kv5usHY7RoFCWNPSYj06J7ltRGeBdsF5BZSLC7yoApcInkiHqEDkOYPMGXZqCZ2tsM_XOWQSuYG_f36svaR8tkuTSmTjqIEzYlUFe2osCpKqn-ELsHXkcvn1tz0UfZQrds2UlDR3s75DQ-b5DHrl8_9W6hKrVAfJPD-Qu2D18UtQ' },
        { rank: 5, name: 'Jean Dupont', score: '920 pts', avatar: 'https://lh3.googleusercontent.com/aida-public/AB6AXuDquzh14Tkm2QC8Sc9hBXGhQqZUy3d_tFhCDsFUiquC-VPbxbkf0zEzOnp3NKS4XLJ3XWg7V6SpqY3U2kCOPMzkGgVYVB4ylEoQCTW6gar-HVjvcP_sx5Xbnb4lTY41Anj6PPzkMx5UBMsTFDuxDq9lbb73q8DJTVS2ZQN0ygs7vWKtR8Uqp_Ej5lj-KcbIEmfvycsAoRmovGg7EgJExo9PP1rTnQYYU-M4xvw2f72tzcsgOqEsyXi1PQ3RejhwQx77Ju1joTLO_yQ' },
        { rank: 6, name: 'Sofia K.', score: '880 pts', avatar: 'https://lh3.googleusercontent.com/aida-public/AB6AXuD6AUkcxGY2SPaM2Tf1-8jUCPeD8wfADgJFOovgnjE531bmf1IfslQ4ASLmkY2JYAzSTeCX4uHmZIwSXXGmgNjqwHkCbKCgTN2u_wBDXFbkcbxjg6cICQifpxWwUM9Hej7MsnayWF2dB5B2IKMjolDzxWHywV5eRNSS-maCrP6q4HGAkqKgv0bwRSwajH88gHz0aadVmbs_jrYN3r5Kbqho2_y5HqcWWgEOMcmDVEMt6NdLichZsSDxyJ6OUzJUv9TkbY7SD-eBMq8' },
        { rank: 7, name: 'Liam Smith', score: '855 pts', avatar: 'https://lh3.googleusercontent.com/aida-public/AB6AXuCGrR8wMO4jm_AGMLKc4_b8-y1BCxWfC19IRNCm6KEbrRaLlTh2sRe72uqXS-JjgWo0_nNgdAr_zY6yWnUOeMCDAo5p-VoNnqI65FAnf-XA1yvY1XSY8k_gtycHSF7M64GZEih4SxmdMkivbIUUOzXQazMO5ybDDBq4lLMgIWGXhTjF9jovXfp4kupMpWL8VGtB4xeMxDySnul6mq_3BQ52b-UMb5ozOPlcddEqdD9jZFhx8XW1jW5UV0_saMmFly8C6PXVeP5OHuw' }
    ],
    currentUser: {
        rank: 42,
        name: 'Koffi L.',
        score: '340 pts'
    }
};

export const contactsData = [
    { id: '1', name: 'Amina Traoré', phone: '+229 97 XX XX 45', isSubscribed: true, avatar: 'https://i.pravatar.cc/150?u=a042581f4e29026024d' },
    { id: '2', name: 'Moussa Diop', phone: '+229 95 XX XX 12', isSubscribed: false, avatar: 'https://i.pravatar.cc/150?u=a042581f4e29026704d' },
    { id: '3', name: 'Fatou N\'Diaye', phone: '+229 64 XX XX 88', isSubscribed: true, avatar: 'https://i.pravatar.cc/150?u=a04258114e29026302d' },
    { id: '4', name: 'Ibrahim Sy', phone: '+229 61 XX XX 33', isSubscribed: false, avatar: 'https://i.pravatar.cc/150?u=a04258114e29026702d' },
];

export const statusTemplates = [
    { id: 't1', text: 'Nouvelle arrivage de sneakers ! 🔥 Contactez-moi vite...', icon: 'shopping_bag' },
    { id: 't2', text: 'Qui pour un foot ce weekend ? ⚽', icon: 'sports_soccer' },
    { id: 't3', text: 'Formation gratuite en marketing digital. Le lien en privé ! 🚀', icon: 'school' }
];
