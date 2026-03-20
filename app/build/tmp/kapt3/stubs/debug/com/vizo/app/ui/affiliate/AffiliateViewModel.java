package com.vizo.app.ui.affiliate;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\u000fJ\u001e\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0015J\u000e\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u0019R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/vizo/app/ui/affiliate/AffiliateViewModel;", "Landroidx/lifecycle/ViewModel;", "affiliateRepository", "Lcom/vizo/app/data/repository/AffiliateRepository;", "userRepository", "Lcom/vizo/app/data/repository/UserRepository;", "(Lcom/vizo/app/data/repository/AffiliateRepository;Lcom/vizo/app/data/repository/UserRepository;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/vizo/app/ui/affiliate/AffiliateUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "clearMessages", "", "loadAffiliateData", "requestWithdrawal", "amount", "", "method", "", "phoneNumber", "selectTab", "tab", "", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class AffiliateViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.vizo.app.data.repository.AffiliateRepository affiliateRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.vizo.app.data.repository.UserRepository userRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.vizo.app.ui.affiliate.AffiliateUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.vizo.app.ui.affiliate.AffiliateUiState> uiState = null;
    
    @javax.inject.Inject()
    public AffiliateViewModel(@org.jetbrains.annotations.NotNull()
    com.vizo.app.data.repository.AffiliateRepository affiliateRepository, @org.jetbrains.annotations.NotNull()
    com.vizo.app.data.repository.UserRepository userRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.vizo.app.ui.affiliate.AffiliateUiState> getUiState() {
        return null;
    }
    
    public final void loadAffiliateData() {
    }
    
    public final void requestWithdrawal(double amount, @org.jetbrains.annotations.NotNull()
    java.lang.String method, @org.jetbrains.annotations.NotNull()
    java.lang.String phoneNumber) {
    }
    
    public final void selectTab(int tab) {
    }
    
    public final void clearMessages() {
    }
}