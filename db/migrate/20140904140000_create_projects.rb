Sequel.migration do
  up do
    create_table(:projects) do
      primary_key :id
      String :name, :size => 200
      String :description, :size => 200
      String :author, :size => 200

      DateTime :created_at
      DateTime :updated_at
    end
  end

  down do
    drop_table(:projects)
  end
end
