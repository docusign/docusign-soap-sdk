class ErrorController < ApplicationController
  attr_accessor :error

  #Surface the error to the user
  def show
    @error = params[:message]
  end
end
