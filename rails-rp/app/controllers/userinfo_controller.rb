class UserinfoController < ApplicationController
  def index
    @sub = params[:sub]
    @email_verified = params[:email_verified]
    @preferred_username = params[:preferred_username]
    @given_name = params[:given_name]
    @family_name = params[:family_name]
  end
end
