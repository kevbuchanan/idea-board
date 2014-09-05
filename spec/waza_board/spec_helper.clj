(ns waza-board.spec-helper
  (:require [korma.db :as db]
            [waza-board.config :refer [read-config]]))

(def test-db (atom nil))

(def ^:private db-config
  (delay (:test (read-config "db.clj"))))

(defn setup-test-db []
  (if @test-db
    @test-db
    (let [db (db/create-db (db/mysql @db-config))]
      (db/default-connection db)
      (reset! test-db db))))

(defn with-test-db [it]
  (let [test-db (setup-test-db)]
    (db/transaction
      (try (it)
           (finally (db/rollback))))))

