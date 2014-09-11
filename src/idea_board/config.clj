(ns idea-board.config
  (:require [clojure.java.io :as io]))

(defn read-config [path]
  (if-let [result (io/resource (format "config/%s" path))]
    (binding [*ns* (the-ns 'idea-board.config)]
      (eval (read-string (slurp result))))
    (throw (Exception. (str "Failed to read config: " path)))))
