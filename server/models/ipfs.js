var mongoose = require("mongoose");

var IPFSSchema = new mongoose.Schema({
    ipfs: String,
    owner: String,
  });


module.exports = mongoose.model("ipfs", IPFSSchema);