(ns waza-board.core-spec
  (:require [speclj.core            :refer :all]
            [cheshire.core          :as c]
            [waza-board.projects    :refer :all]
            [waza-board.spec-helper :refer :all]
            [waza-board.core        :refer :all]))

(defn do-get [route]
  (handler {:request-method :get :uri route}))

(defn do-post [route params]
  (handler {:request-method :post :uri route :params params}))

(context "projects"

  (around [it] (with-test-db it))

  (describe "GET /projects"
    (it "returns 200"
      (let [response (do-get "/projects")]
        (should= 200 (:status response))))

    (it "returns all projects as json"
      (create-project {:name "the name"})
      (let [response (do-get "/projects")
            projects (c/parse-string (:body response))]
        (should= 1 (count projects))))
  )

  (describe "POST /projects"
    (it "creates a project"
      (do-post "/projects" {:name "My project"})
      (should= "My project" (:name (first (all-projects)))))
  )
)