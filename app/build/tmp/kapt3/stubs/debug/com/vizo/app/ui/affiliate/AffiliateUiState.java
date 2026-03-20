package com.vizo.app.ui.affiliate;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b$\b\u0087\b\u0018\u00002\u00020\u0001B\u0089\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0007\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0007\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0010\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u000e\u00a2\u0006\u0002\u0010\u0015J\t\u0010$\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010&\u001a\u00020\u000eH\u00c6\u0003J\t\u0010\'\u001a\u00020\u0005H\u00c6\u0003J\u000f\u0010(\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0003J\u000f\u0010)\u001a\b\u0012\u0004\u0012\u00020\n0\u0007H\u00c6\u0003J\u000f\u0010*\u001a\b\u0012\u0004\u0012\u00020\f0\u0007H\u00c6\u0003J\t\u0010+\u001a\u00020\u000eH\u00c6\u0003J\t\u0010,\u001a\u00020\u0010H\u00c6\u0003J\t\u0010-\u001a\u00020\u0010H\u00c6\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u008d\u0001\u0010/\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00072\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00072\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0014\u001a\u00020\u000eH\u00c6\u0001J\u0013\u00100\u001a\u00020\u00102\b\u00101\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00102\u001a\u00020\u000eH\u00d6\u0001J\t\u00103\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u001cR\u0011\u0010\u0011\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u001cR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001bR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0019R\u0011\u0010\u0014\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001bR\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010 R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0019\u00a8\u00064"}, d2 = {"Lcom/vizo/app/ui/affiliate/AffiliateUiState;", "", "referralCode", "", "balance", "", "referrals", "", "Lcom/vizo/app/data/model/Referral;", "commissions", "Lcom/vizo/app/data/model/Commission;", "withdrawals", "Lcom/vizo/app/data/model/Withdrawal;", "weeklyWithdrawals", "", "isLoading", "", "isWithdrawing", "error", "successMessage", "selectedTab", "(Ljava/lang/String;DLjava/util/List;Ljava/util/List;Ljava/util/List;IZZLjava/lang/String;Ljava/lang/String;I)V", "getBalance", "()D", "getCommissions", "()Ljava/util/List;", "getError", "()Ljava/lang/String;", "()Z", "getReferralCode", "getReferrals", "getSelectedTab", "()I", "getSuccessMessage", "getWeeklyWithdrawals", "getWithdrawals", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "app_debug"})
public final class AffiliateUiState {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String referralCode = null;
    private final double balance = 0.0;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.vizo.app.data.model.Referral> referrals = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.vizo.app.data.model.Commission> commissions = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.vizo.app.data.model.Withdrawal> withdrawals = null;
    private final int weeklyWithdrawals = 0;
    private final boolean isLoading = false;
    private final boolean isWithdrawing = false;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String error = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String successMessage = null;
    private final int selectedTab = 0;
    
    public AffiliateUiState(@org.jetbrains.annotations.NotNull()
    java.lang.String referralCode, double balance, @org.jetbrains.annotations.NotNull()
    java.util.List<com.vizo.app.data.model.Referral> referrals, @org.jetbrains.annotations.NotNull()
    java.util.List<com.vizo.app.data.model.Commission> commissions, @org.jetbrains.annotations.NotNull()
    java.util.List<com.vizo.app.data.model.Withdrawal> withdrawals, int weeklyWithdrawals, boolean isLoading, boolean isWithdrawing, @org.jetbrains.annotations.Nullable()
    java.lang.String error, @org.jetbrains.annotations.Nullable()
    java.lang.String successMessage, int selectedTab) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getReferralCode() {
        return null;
    }
    
    public final double getBalance() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.vizo.app.data.model.Referral> getReferrals() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.vizo.app.data.model.Commission> getCommissions() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.vizo.app.data.model.Withdrawal> getWithdrawals() {
        return null;
    }
    
    public final int getWeeklyWithdrawals() {
        return 0;
    }
    
    public final boolean isLoading() {
        return false;
    }
    
    public final boolean isWithdrawing() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getError() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getSuccessMessage() {
        return null;
    }
    
    public final int getSelectedTab() {
        return 0;
    }
    
    public AffiliateUiState() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component10() {
        return null;
    }
    
    public final int component11() {
        return 0;
    }
    
    public final double component2() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.vizo.app.data.model.Referral> component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.vizo.app.data.model.Commission> component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.vizo.app.data.model.Withdrawal> component5() {
        return null;
    }
    
    public final int component6() {
        return 0;
    }
    
    public final boolean component7() {
        return false;
    }
    
    public final boolean component8() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.vizo.app.ui.affiliate.AffiliateUiState copy(@org.jetbrains.annotations.NotNull()
    java.lang.String referralCode, double balance, @org.jetbrains.annotations.NotNull()
    java.util.List<com.vizo.app.data.model.Referral> referrals, @org.jetbrains.annotations.NotNull()
    java.util.List<com.vizo.app.data.model.Commission> commissions, @org.jetbrains.annotations.NotNull()
    java.util.List<com.vizo.app.data.model.Withdrawal> withdrawals, int weeklyWithdrawals, boolean isLoading, boolean isWithdrawing, @org.jetbrains.annotations.Nullable()
    java.lang.String error, @org.jetbrains.annotations.Nullable()
    java.lang.String successMessage, int selectedTab) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}