(ns waza-board.projects
  (:require [korma.core :as k]))

(k/defentity projects
  (k/entity-fields :name :description :author))

(defn create-project [attrs]
  (k/insert projects
    (k/values attrs)))

(defn all-projects []
  (k/select projects))
