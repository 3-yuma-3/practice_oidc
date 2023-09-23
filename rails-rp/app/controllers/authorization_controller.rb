class AuthorizationController < ApplicationController
  def create
    redirect_to authorization_endpoint
  end

  def authorization_endpoint
    "http://localhost:8880/realms/#{client_id}/protocol/openid-connect/auth#{query_parameters}"
  end

  def client_id
    "rails-rp"
  end

  def query_parameters
    "?response_type=code&client_id=#{client_id}&scope=openid&redirect_uri=#{redirect_uri}"
  end

  def redirect_uri
    "http://localhost:3000/callback"
  end
end
