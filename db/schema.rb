Sequel.migration do
  change do
    create_table(:projects) do
      primary_key :id
      String :name, :size=>200
      String :description, :size=>200
      String :author, :size=>200
      DateTime :created_at
      DateTime :updated_at
    end

    create_table(:schema_migrations) do
      String :filename, :size=>255, :null=>false

      primary_key [:filename]
    end
  end
end
