package com.vizo.app.ui.affiliate;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000Z\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0007\u001a<\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00010\u000eH\u0007\u001a\u0010\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\nH\u0007\u001a\u0016\u0010\u0012\u001a\u00020\u00012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014H\u0007\u001a\u0018\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\nH\u0007\u001a\u0016\u0010\u0019\u001a\u00020\u00012\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0014H\u0007\u001aF\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\u001e2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00010\u000e2\u001e\u0010 \u001a\u001a\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010!H\u0007\u001a\u0016\u0010\"\u001a\u00020\u00012\f\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\u0014H\u0007\u00a8\u0006%"}, d2 = {"AffiliateScreen", "", "navController", "Landroidx/navigation/NavController;", "viewModel", "Lcom/vizo/app/ui/affiliate/AffiliateViewModel;", "BalanceCard", "balance", "", "referralCode", "", "weeklyWithdrawals", "", "onWithdraw", "Lkotlin/Function0;", "onShare", "CommissionStatusBadge", "status", "CommissionsTab", "commissions", "", "Lcom/vizo/app/data/model/Commission;", "EmptyState", "icon", "message", "ReferralsTab", "referrals", "Lcom/vizo/app/data/model/Referral;", "WithdrawDialog", "isLoading", "", "onDismiss", "onConfirm", "Lkotlin/Function3;", "WithdrawalsTab", "withdrawals", "Lcom/vizo/app/data/model/Withdrawal;", "app_debug"})
public final class AffiliateScreenKt {
    
    @androidx.compose.runtime.Composable()
    public static final void AffiliateScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.vizo.app.ui.affiliate.AffiliateViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void BalanceCard(double balance, @org.jetbrains.annotations.NotNull()
    java.lang.String referralCode, int weeklyWithdrawals, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onWithdraw, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onShare) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void CommissionsTab(@org.jetbrains.annotations.NotNull()
    java.util.List<com.vizo.app.data.model.Commission> commissions) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void CommissionStatusBadge(@org.jetbrains.annotations.NotNull()
    java.lang.String status) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void ReferralsTab(@org.jetbrains.annotations.NotNull()
    java.util.List<com.vizo.app.data.model.Referral> referrals) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void WithdrawalsTab(@org.jetbrains.annotations.NotNull()
    java.util.List<com.vizo.app.data.model.Withdrawal> withdrawals) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void WithdrawDialog(double balance, boolean isLoading, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function3<? super java.lang.Double, ? super java.lang.String, ? super java.lang.String, kotlin.Unit> onConfirm) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void EmptyState(@org.jetbrains.annotations.NotNull()
    java.lang.String icon, @org.jetbrains.annotations.NotNull()
    java.lang.String message) {
    }
}