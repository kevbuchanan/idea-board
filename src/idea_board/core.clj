(ns idea-board.core
  (:require [korma.db                       :refer [mysql defdb]]
            [compojure.core                 :refer :all]
            [compojure.route                :refer [not-found]]
            [hiccup.def                     :refer [defhtml]]
            [hiccup.page                    :refer [include-css include-js]]
            [ring.adapter.jetty             :refer [run-jetty]]
            [ring.util.response             :refer [response]]
            [ring.middleware.resource       :refer [wrap-resource]]
            [ring.middleware.json           :refer :all]
            [ring.middleware.params         :refer [wrap-params]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [idea-board.projects            :refer :all]
            [idea-board.config              :refer [read-config]]))

(def ^:private db-config
  (delay (:development (read-config "db.clj"))))

(defdb db (mysql @db-config))

(defhtml index-html []
  [:head
    [:title "Waza Board"]
    (include-css "/app.css")]
  [:body
    (include-js "/app.js")])

(defroutes app
  (context "/api" []
    (GET "/projects" []
      (response (all-projects)))
    (POST "/projects" {params :params}
      (response (create-project params)))
  )
  (GET "*" []
    (response (index-html)))
)

(def handler
  (-> app
    (wrap-resource "assets")
    wrap-params
    wrap-keyword-params
    wrap-json-body
    wrap-json-response))

(defn -main [& args]
  (run-jetty handler {:port 5000}))
