var express = require('express');
var router = express.Router();

router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});

router.get('/adduser', (req,res)=>{
	res.render("adduser");
})
module.exports = router;
