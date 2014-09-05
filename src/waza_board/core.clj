(ns waza-board.core
  (:require [korma.db                       :refer :all]
            [ring.adapter.jetty             :refer [run-jetty]]
            [ring.util.response             :refer [response]]
            [ring.middleware.json           :refer :all]
            [compojure.core                 :refer :all]
            [ring.middleware.params         :refer [wrap-params]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [waza-board.projects            :refer :all]
            [waza-board.config              :refer [read-config]]))

(def ^:private db-config
  (delay (:development (read-config "db.clj"))))

(defdb db (mysql @db-config))

(defroutes app
  (GET "/projects" [] (response (all-projects)))
  (POST "/projects" request (response (create-project (:params request))))
)

(def handler
  (-> app
    wrap-json-response
    wrap-json-body
    wrap-json-params
    wrap-params))

(defn -main [& args]
  (run-jetty handler {:port 5000}))
