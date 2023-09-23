FROM ruby:3.2.2

RUN gem update --system 3.4.19

COPY Gemfile .
COPY Gemfile.lock .

RUN bundle
