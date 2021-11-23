public class DatabaseManager {
    private static DatabaseManager instance;
    Database database;

    private DatabaseManager()
    {

    }
    public static DatabaseManager getInstance(){
        if(instance == null){
            instance = new DatabaseManager();
        }
        return instance;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }
    public void execOperation(DBOperation dbo, User user){

    }
}
