require 'net/http'
require 'uri'

class CallbackController < ApplicationController

  def exchange
    token_res = self.token_request

    access_token = token_res["access_token"]
    expires_in = token_res["expires_in"]
    refresh_expires_in = token_res["refresh_expires_in"]
    refresh_token = token_res["refresh_token"]
    token_type = token_res["token_type"]
    id_token = token_res["id_token"]
    not_before_policy = token_res["not-before-policy"]
    session_state = token_res["session_state"]
    scope = token_res["scope"]
  end

  private

  def token_request
    res = Net::HTTP.post_form(
      URI.parse(token_endpoint),
      self.token_req_body
    )
    JSON.parse(res.body)
  end

  def token_req_body
    {
      'client_id' => client_id,
      'client_secret' => client_secret,
      'grant_type' => 'authorization_code',
      'code' => code,
      'redirect_uri' => redirect_uri
    }
  end

  def token_endpoint
    "http://host.docker.internal:8880/realms/#{client_id}/protocol/openid-connect/token"
  end

  def code
    code = params[:code]
  end

  def client_id
    "rails-rp"
  end

  def client_secret
    "Qibm7Ge3uv9zZgSBnvc5OCaMNaMDUW7M"
  end

  def redirect_uri
    "http://localhost:3000/callback"
  end
end
