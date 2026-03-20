package com.vizo.app.ui.boost;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\f\u001a\u00020\rJ\u0006\u0010\u000e\u001a\u00020\rJ\u000e\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0012"}, d2 = {"Lcom/vizo/app/ui/boost/BoostViewModel;", "Landroidx/lifecycle/ViewModel;", "subscriptionRepository", "Lcom/vizo/app/data/repository/SubscriptionRepository;", "(Lcom/vizo/app/data/repository/SubscriptionRepository;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/vizo/app/ui/boost/BoostUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "clearMessages", "", "loadBoost", "purchaseBoost", "duration", "", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class BoostViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.vizo.app.data.repository.SubscriptionRepository subscriptionRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.vizo.app.ui.boost.BoostUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.vizo.app.ui.boost.BoostUiState> uiState = null;
    
    @javax.inject.Inject()
    public BoostViewModel(@org.jetbrains.annotations.NotNull()
    com.vizo.app.data.repository.SubscriptionRepository subscriptionRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.vizo.app.ui.boost.BoostUiState> getUiState() {
        return null;
    }
    
    public final void loadBoost() {
    }
    
    public final void purchaseBoost(int duration) {
    }
    
    public final void clearMessages() {
    }
}