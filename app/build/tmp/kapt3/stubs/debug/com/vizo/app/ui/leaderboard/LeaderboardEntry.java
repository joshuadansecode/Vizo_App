package com.vizo.app.ui.leaderboard;

@kotlinx.serialization.Serializable()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u001b\b\u0087\b\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u00a2\u0006\u0002\u0010\nJ\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0006H\u00c6\u0003J\u0010\u0010\u001b\u001a\u0004\u0018\u00010\bH\u00c6\u0003\u00a2\u0006\u0002\u0010\u0011J\t\u0010\u001c\u001a\u00020\bH\u00c6\u0003JD\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\t\u001a\u00020\bH\u00c6\u0001\u00a2\u0006\u0002\u0010\u001eJ\u0013\u0010\u001f\u001a\u00020\u00062\b\u0010 \u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010!\u001a\u00020\bH\u00d6\u0001J\t\u0010\"\u001a\u00020\u0003H\u00d6\u0001R\u001c\u0010\t\u001a\u00020\b8\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR \u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004\u00a2\u0006\u0010\n\u0002\u0010\u0012\u0012\u0004\b\u000f\u0010\f\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\f\u001a\u0004\b\u0005\u0010\u0016R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014\u00a8\u0006#"}, d2 = {"Lcom/vizo/app/ui/leaderboard/LeaderboardEntry;", "", "id", "", "name", "isFounder", "", "founderRank", "", "contactCount", "(Ljava/lang/String;Ljava/lang/String;ZLjava/lang/Integer;I)V", "getContactCount$annotations", "()V", "getContactCount", "()I", "getFounderRank$annotations", "getFounderRank", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getId", "()Ljava/lang/String;", "isFounder$annotations", "()Z", "getName", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/String;Ljava/lang/String;ZLjava/lang/Integer;I)Lcom/vizo/app/ui/leaderboard/LeaderboardEntry;", "equals", "other", "hashCode", "toString", "app_debug"})
public final class LeaderboardEntry {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String id = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String name = null;
    private final boolean isFounder = false;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer founderRank = null;
    private final int contactCount = 0;
    
    public LeaderboardEntry(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.Nullable()
    java.lang.String name, boolean isFounder, @org.jetbrains.annotations.Nullable()
    java.lang.Integer founderRank, int contactCount) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getName() {
        return null;
    }
    
    public final boolean isFounder() {
        return false;
    }
    
    @kotlinx.serialization.SerialName(value = "is_founder")
    @java.lang.Deprecated()
    public static void isFounder$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getFounderRank() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "founder_rank")
    @java.lang.Deprecated()
    public static void getFounderRank$annotations() {
    }
    
    public final int getContactCount() {
        return 0;
    }
    
    @kotlinx.serialization.SerialName(value = "contact_count")
    @java.lang.Deprecated()
    public static void getContactCount$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component2() {
        return null;
    }
    
    public final boolean component3() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component4() {
        return null;
    }
    
    public final int component5() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.vizo.app.ui.leaderboard.LeaderboardEntry copy(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.Nullable()
    java.lang.String name, boolean isFounder, @org.jetbrains.annotations.Nullable()
    java.lang.Integer founderRank, int contactCount) {
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