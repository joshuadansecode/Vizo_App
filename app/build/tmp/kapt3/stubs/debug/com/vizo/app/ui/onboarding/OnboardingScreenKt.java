package com.vizo.app.ui.onboarding;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000&\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\u001a \u0010\u0004\u001a\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0007\u001a$\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u00032\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\fH\u0007\u001a\b\u0010\r\u001a\u00020\u0001H\u0007\u001a$\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00032\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\fH\u0007\u001a\b\u0010\u0011\u001a\u00020\u0001H\u0007\u00a8\u0006\u0012"}, d2 = {"FeatureChip", "", "text", "", "OnboardingScreen", "onComplete", "Lkotlin/Function0;", "viewModel", "Lcom/vizo/app/ui/onboarding/OnboardingViewModel;", "StepName", "name", "onNameChange", "Lkotlin/Function1;", "StepReady", "StepReferral", "code", "onCodeChange", "StepWelcome", "app_debug"})
public final class OnboardingScreenKt {
    
    @androidx.compose.runtime.Composable()
    public static final void OnboardingScreen(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onComplete, @org.jetbrains.annotations.NotNull()
    com.vizo.app.ui.onboarding.OnboardingViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void StepWelcome() {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void StepName(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onNameChange) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void StepReferral(@org.jetbrains.annotations.NotNull()
    java.lang.String code, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onCodeChange) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void StepReady() {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void FeatureChip(@org.jetbrains.annotations.NotNull()
    java.lang.String text) {
    }
}