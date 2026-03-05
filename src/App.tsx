import React from 'react'
import { DashboardPage } from './pages/DashboardPage'
import { LoginPage } from './pages/LoginPage'
import { SignupPage } from './pages/SignupPage'
import { OnboardingPage } from './pages/OnboardingPage'
import { ProfilePage } from './pages/ProfilePage'
import { BoostsPage } from './pages/BoostsPage'
import { LeaderboardPage } from './pages/LeaderboardPage'
import { ContactsPage } from './pages/ContactsPage'
import { CreateStatusPage } from './pages/CreateStatusPage'
import { NavigationProvider, useNavigation, type Page } from './context/NavigationContext'
import { Toaster } from 'sonner'
import { motion, AnimatePresence } from 'framer-motion'
import './index.css'

const AppContent: React.FC = () => {
  const { currentPage, navigate } = useNavigation();
  const pages: Page[] = ['onboarding', 'login', 'signup', 'dashboard', 'profile', 'boosts', 'leaderboard', 'contacts', 'create']

  return (
    <div className="relative">
      {/* Dev Navigation Toggle (Temporary) */}
      <div className="fixed top-2 left-1/2 -translate-x-1/2 z-[100] flex flex-wrap justify-center gap-2 bg-slate-800/80 backdrop-blur rounded-2xl p-2 shadow-xl text-xs max-w-[90vw]">
        {pages.map(page => (
          <button
            key={page}
            onClick={() => navigate(page)}
            className={`px-3 py-1 rounded-full capitalize ${currentPage === page ? 'bg-primary text-black font-bold' : 'text-white hover:bg-white/10'}`}
          >
            {page}
          </button>
        ))}
      </div>
      <Toaster position="top-center" theme="dark" richColors expand={true} />

      <AnimatePresence mode="wait">
        <motion.div
          key={currentPage}
          initial={{ opacity: 0, filter: 'blur(4px)', y: 10 }}
          animate={{ opacity: 1, filter: 'blur(0px)', y: 0 }}
          exit={{ opacity: 0, filter: 'blur(4px)', y: -10 }}
          transition={{ duration: 0.2, ease: "easeOut" }}
          className="w-full min-h-screen"
        >
          {currentPage === 'onboarding' && <OnboardingPage />}
          {currentPage === 'login' && <LoginPage />}
          {currentPage === 'signup' && <SignupPage />}
          {currentPage === 'dashboard' && <DashboardPage />}
          {currentPage === 'profile' && <ProfilePage />}
          {currentPage === 'boosts' && <BoostsPage />}
          {currentPage === 'leaderboard' && <LeaderboardPage />}
          {currentPage === 'contacts' && <ContactsPage />}
          {currentPage === 'create' && <CreateStatusPage />}
        </motion.div>
      </AnimatePresence>
    </div>
  )
}

function App() {
  return (
    <NavigationProvider>
      <AppContent />
    </NavigationProvider>
  )
}

export default App
