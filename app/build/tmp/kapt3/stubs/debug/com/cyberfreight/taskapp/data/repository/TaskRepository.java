package com.cyberfreight.taskapp.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0012\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nJ\u0018\u0010\r\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u000e0\nJ\u000e\u0010\u000f\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0011J\"\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u000eH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0013\u0010\u0011R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u0014"}, d2 = {"Lcom/cyberfreight/taskapp/data/repository/TaskRepository;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "apiService", "Lcom/cyberfreight/taskapp/data/api/ApiService;", "taskDao", "Lcom/cyberfreight/taskapp/data/local/TaskDao;", "getCachedTasks", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/cyberfreight/taskapp/data/model/Task;", "getTasks", "Lkotlin/Result;", "hasCachedData", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "refreshTasks", "refreshTasks-IoAF18A", "app_debug"})
public final class TaskRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.cyberfreight.taskapp.data.local.TaskDao taskDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.cyberfreight.taskapp.data.api.ApiService apiService = null;
    
    public TaskRepository(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<kotlin.Result<java.util.List<com.cyberfreight.taskapp.data.model.Task>>> getTasks() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.cyberfreight.taskapp.data.model.Task>> getCachedTasks() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object hasCachedData(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
}