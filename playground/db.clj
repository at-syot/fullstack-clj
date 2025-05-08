(comment
  (require '[next.jdbc :as jdbc]
           '[next.jdbc.result-set :refer [as-unqualified-lower-maps]])
  (require '[honey.sql :as sql]
           '[honey.sql.helpers 
             :refer [select from where 
                     insert-into values
                     create-table drop-table 
                     columns with-columns] 
             :as h])

  (def db {:dbtype "postgres" 
           :dbname "poc_db"
           :user "pocuser"
           :password "pocpassword"
           :host "localhost"})

  (def ds (jdbc/get-datasource db))
  (jdbc/execute-one! 
    ds 
    (-> (create-table :users)
        (with-columns 
          [:id :serial :primary-key]
          [:name [:varchar 50]])
        sql/format))

  (jdbc/execute-one!
    ds 
    (-> (drop-table :users)
        (sql/format)))
  
  (jdbc/execute-one! 
    ds 
    (-> (insert-into :users [:name])
        (values [{:name "oat"}])
        sql/format))

  (jdbc/execute!
    ds
    (-> (select :*)
        (from :users)
        (sql/format))
    {:builder-fn as-unqualified-lower-maps}))
  

  
