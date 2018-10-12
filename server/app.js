var createError = require('http-errors');
var express = require('express');
var mongoose = require("mongoose");
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');
var User = require("./models/user");

var indexRouter = require('./routes/index');

mongoose.connect('mongodb://vinayak:qwerty123@ds263571.mlab.com:63571/blockpal', { useNewUrlParser: true }).then(function(data){
	console.log("database set up")
}).catch(function(err) {
	console.log(err)
});

var app = express();

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

app.use('/', indexRouter);

app.use(function(req, res, next) {
  next(createError(404));
});

app.use(function(err, req, res, next) {
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};
  res.status(err.status || 500);
  res.render('error');
});

module.exports = app;
