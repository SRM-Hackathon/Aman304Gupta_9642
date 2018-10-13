var mongoose = require("mongoose");

var AmountSchema = new mongoose.Schema({
    email: String,
    amount: Number,
});


module.exports = mongoose.model("amount", AmountSchema);