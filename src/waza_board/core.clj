(ns waza-board.core
  (:require [korma.db                       :refer [mysql defdb]]
            [compojure.core                 :refer :all]
            [compojure.route                :refer [not-found]]
            [hiccup.def                     :refer [defhtml]]
            [hiccup.page                    :refer [include-js]]
            [ring.adapter.jetty             :refer [run-jetty]]
            [ring.util.response             :refer [response]]
            [ring.middleware.file           :refer [wrap-file]]
            [ring.middleware.json           :refer :all]
            [ring.middleware.params         :refer [wrap-params]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [waza-board.projects            :refer :all]
            [waza-board.config              :refer [read-config]]))

(def ^:private db-config
  (delay (:development (read-config "db.clj"))))

(defdb db (mysql @db-config))

(defhtml index-html []
  [:head
    [:title "Waza Board"]]
  [:body
    (include-js "/javascripts/app.js")])

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
    (wrap-file "src/assets")
    wrap-params
    wrap-keyword-params
    wrap-json-body
    wrap-json-response))

(defn -main [& args]
  (run-jetty handler {:port 5000}))
