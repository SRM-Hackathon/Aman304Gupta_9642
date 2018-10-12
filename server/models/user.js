var mongoose = require("mongoose");

var UserSchema = new mongoose.Schema({
    username: String,
    startup: String,
    idea: String,
    linkdIn: String,
    contract_addr: String,
    amount_needed: String
});


module.exports = mongoose.model("User", UserSchema);