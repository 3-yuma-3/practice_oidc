Rails.application.routes.draw do
  get 'userinfo/index'
  get 'top/index'
  # Define your application routes per the DSL in https://guides.rubyonrails.org/routing.html

  # Defines the root path route ("/")
  root "top#index"

  post "authorization", to: "authorization#create"
  get "callback", to: "callback#exchange"
end
