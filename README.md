# React + TypeScript + Vite

This template provides a minimal setup to get React working in Vite with HMR and some ESLint rules.

Currently, two official plugins are available:

- [@vitejs/plugin-react](https://github.com/vitejs/vite-plugin-react/blob/main/packages/plugin-react) uses [Babel](https://babeljs.io/) (or [oxc](https://oxc.rs) when used in [rolldown-vite](https://vite.dev/guide/rolldown)) for Fast Refresh
- [@vitejs/plugin-react-swc](https://github.com/vitejs/vite-plugin-react/blob/main/packages/plugin-react/blob/main/packages/plugin-react-swc) uses [SWC](https://swc.rs/) for Fast Refresh

## ⚠️ Critical Build Blocker
The project currently fails to build via `gradlew` because the system is running **Java 25.0.1**.
- **Issue**: The Kotlin compiler (1.9.22/2.x) does not recognize the version string "25.0.1" and throws an `IllegalArgumentException` during the script compilation phase.
- **Fix**: You should switch the JDK in Android Studio (or your shell) to a stable version like **Java 17** or **Java 21**.

## 🚀 Feature Status
### Splash Screen & Session Verification
- **Status**: ✅ Implemented.
- **Logic**: Automatically checks Supabase session. Redirects to `PhoneScreen` (Auth), `OnboardingScreen`, or `DashboardScreen` based on user state and profile existence.

### "Create" Status
- **Status**: ✅ Implemented.
- **Features**: 
    - Title and content fields with character counting (max 700).
    - Status templates (Category: Quote, News, Promotion, personal).
    - **WhatsApp Integration**: Allows direct sharing to WhatsApp with automatic formatting (bold titles).
    - **Drafts**: Can save statuses as drafts in Supabase.

## 🧩 UI/Web Components
The `temp_htmls` folder contains design templates for `boosts`, `leaderboard`, and `profile`. These seem to serve as the visual reference for the Compose screens.

## React Compiler

The React Compiler is not enabled on this template because of its impact on dev & build performances. To add it, see [this documentation](https://react.dev/learn/react-compiler/installation).

## Expanding the ESLint configuration

If you are developing a production application, we recommend updating the configuration to enable type-aware lint rules:

```js
export default defineConfig([
  globalIgnores(['dist']),
  {
    files: ['**/*.{ts,tsx}'],
    extends: [
      // Other configs...

      // Remove tseslint.configs.recommended and replace with this
      tseslint.configs.recommendedTypeChecked,
      // Alternatively, use this for stricter rules
      tseslint.configs.strictTypeChecked,
      // Optionally, add this for stylistic rules
      tseslint.configs.stylisticTypeChecked,

      // Other configs...
    ],
    languageOptions: {
      parserOptions: {
        project: ['./tsconfig.node.json', './tsconfig.app.json'],
        tsconfigRootDir: import.meta.dirname,
      },
      // other options...
    },
  },
])
```

You can also install [eslint-plugin-react-x](https://github.com/Rel1cx/eslint-react/tree/main/packages/plugins/eslint-plugin-react-x) and [eslint-plugin-react-dom](https://github.com/Rel1cx/eslint-react/tree/main/packages/plugins/eslint-plugin-react-dom) for React-specific lint rules:

```js
// eslint.config.js
import reactX from 'eslint-plugin-react-x'
import reactDom from 'eslint-plugin-react-dom'

export default defineConfig([
  globalIgnores(['dist']),
  {
    files: ['**/*.{ts,tsx}'],
    extends: [
      // Other configs...
      // Enable lint rules for React
      reactX.configs['recommended-typescript'],
      // Enable lint rules for React DOM
      reactDom.configs.recommended,
    ],
    languageOptions: {
      parserOptions: {
        project: ['./tsconfig.node.json', './tsconfig.app.json'],
        tsconfigRootDir: import.meta.dirname,
      },
      // other options...
    },
  },
])
```
