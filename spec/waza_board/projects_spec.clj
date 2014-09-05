(ns waza-board.projects-spec
  (:require [speclj.core            :refer :all]
            [waza-board.spec-helper :refer :all]
            [waza-board.projects    :refer :all]))

(defn make-project [& attrs]
  (let [default-attrs {:name "Name" :description "Desc" :author "Author"}]
    (create-project (merge default-attrs (first attrs)))))

(context "projects"

  (around [it] (with-test-db it))

  (describe "create-project"
    (it "saves the project"
      (make-project {:name "one"})
      (should= "one" (:name (first (all-projects))))))

  (describe "all-projects"
    (it "returns all projects"
      (make-project)
      (make-project)
      (let [results (all-projects)]
        (should= 2 (count results))))
  )
)

