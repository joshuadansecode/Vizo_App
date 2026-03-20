package com.vizo.app.ui.contacts;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016J\u0006\u0010\u0018\u001a\u00020\u0014J\u0006\u0010\u0019\u001a\u00020\u0014J\u000e\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u0016J\u000e\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u001d\u001a\u00020\u0016J\u000e\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u0016R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b8F\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\t0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012\u00a8\u0006 "}, d2 = {"Lcom/vizo/app/ui/contacts/ContactsViewModel;", "Landroidx/lifecycle/ViewModel;", "contactRepository", "Lcom/vizo/app/data/repository/ContactRepository;", "subscriptionRepository", "Lcom/vizo/app/data/repository/SubscriptionRepository;", "(Lcom/vizo/app/data/repository/ContactRepository;Lcom/vizo/app/data/repository/SubscriptionRepository;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/vizo/app/ui/contacts/ContactsUiState;", "filteredContacts", "", "Lcom/vizo/app/data/model/Contact;", "getFilteredContacts", "()Ljava/util/List;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "addContact", "", "phoneNumber", "", "name", "clearMessages", "loadContacts", "removeContact", "contactId", "updateFilter", "filter", "updateSearch", "query", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class ContactsViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.vizo.app.data.repository.ContactRepository contactRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.vizo.app.data.repository.SubscriptionRepository subscriptionRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.vizo.app.ui.contacts.ContactsUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.vizo.app.ui.contacts.ContactsUiState> uiState = null;
    
    @javax.inject.Inject()
    public ContactsViewModel(@org.jetbrains.annotations.NotNull()
    com.vizo.app.data.repository.ContactRepository contactRepository, @org.jetbrains.annotations.NotNull()
    com.vizo.app.data.repository.SubscriptionRepository subscriptionRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.vizo.app.ui.contacts.ContactsUiState> getUiState() {
        return null;
    }
    
    public final void loadContacts() {
    }
    
    public final void addContact(@org.jetbrains.annotations.NotNull()
    java.lang.String phoneNumber, @org.jetbrains.annotations.Nullable()
    java.lang.String name) {
    }
    
    public final void removeContact(@org.jetbrains.annotations.NotNull()
    java.lang.String contactId) {
    }
    
    public final void updateSearch(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
    }
    
    public final void updateFilter(@org.jetbrains.annotations.NotNull()
    java.lang.String filter) {
    }
    
    public final void clearMessages() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.vizo.app.data.model.Contact> getFilteredContacts() {
        return null;
    }
}