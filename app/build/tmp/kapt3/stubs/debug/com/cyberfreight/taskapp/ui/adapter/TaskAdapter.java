package com.cyberfreight.taskapp.ui.adapter;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002\u000e\u000fB\u0005\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0016J\u0018\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\tH\u0016\u00a8\u0006\u0010"}, d2 = {"Lcom/cyberfreight/taskapp/ui/adapter/TaskAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/cyberfreight/taskapp/data/model/Task;", "Lcom/cyberfreight/taskapp/ui/adapter/TaskAdapter$TaskViewHolder;", "()V", "onBindViewHolder", "", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "TaskDiffCallback", "TaskViewHolder", "app_debug"})
public final class TaskAdapter extends androidx.recyclerview.widget.ListAdapter<com.cyberfreight.taskapp.data.model.Task, com.cyberfreight.taskapp.ui.adapter.TaskAdapter.TaskViewHolder> {
    
    public TaskAdapter() {
        super(null);
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.cyberfreight.taskapp.ui.adapter.TaskAdapter.TaskViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.cyberfreight.taskapp.ui.adapter.TaskAdapter.TaskViewHolder holder, int position) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/cyberfreight/taskapp/ui/adapter/TaskAdapter$TaskDiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/cyberfreight/taskapp/data/model/Task;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_debug"})
    static final class TaskDiffCallback extends androidx.recyclerview.widget.DiffUtil.ItemCallback<com.cyberfreight.taskapp.data.model.Task> {
        
        public TaskDiffCallback() {
            super();
        }
        
        @java.lang.Override()
        public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull()
        com.cyberfreight.taskapp.data.model.Task oldItem, @org.jetbrains.annotations.NotNull()
        com.cyberfreight.taskapp.data.model.Task newItem) {
            return false;
        }
        
        @java.lang.Override()
        public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull()
        com.cyberfreight.taskapp.data.model.Task oldItem, @org.jetbrains.annotations.NotNull()
        com.cyberfreight.taskapp.data.model.Task newItem) {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/cyberfreight/taskapp/ui/adapter/TaskAdapter$TaskViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/cyberfreight/taskapp/databinding/ItemTaskBinding;", "(Lcom/cyberfreight/taskapp/databinding/ItemTaskBinding;)V", "bind", "", "task", "Lcom/cyberfreight/taskapp/data/model/Task;", "app_debug"})
    public static final class TaskViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.cyberfreight.taskapp.databinding.ItemTaskBinding binding = null;
        
        public TaskViewHolder(@org.jetbrains.annotations.NotNull()
        com.cyberfreight.taskapp.databinding.ItemTaskBinding binding) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.cyberfreight.taskapp.data.model.Task task) {
        }
    }
}