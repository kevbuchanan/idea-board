(ns idea-board.core-spec
  (:require [speclj.core            :refer :all]
            [cheshire.core          :as c]
            [idea-board.projects    :refer :all]
            [idea-board.spec-helper :refer :all]
            [idea-board.core        :refer :all]))

(defn do-get [route]
  (handler {:request-method :get :uri route}))

(defn do-post [route params]
  (handler {:request-method :post :uri route :params params}))

(context "projects"

  (around [it] (with-test-db it))

  (describe "GET /api/projects"
    (it "returns 200"
      (let [response (do-get "/api/projects")]
        (should= 200 (:status response))))

    (it "returns all projects as json"
      (create-project {:name "the name"})
      (let [response (do-get "/api/projects")
            projects (c/parse-string (:body response))]
        (should= 1 (count projects))))
  )

  (describe "POST /api/projects"
    (it "creates a project"
      (do-post "/api/projects" {:name "My project"})
      (should= "My project" (:name (first (all-projects)))))
  )
)
