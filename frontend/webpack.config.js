const path = require('path')
var HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
    entry: './index.js',
    output: {
        path: path.join(__dirname, '../src/main/webapp/js/webpack/dist'),
        filename: 'index_bundle.js',
    },
    devServer: {
        port: 3000,
    },
    resolve: {
        extensions: ['.js', '.jsx', '.ts'],
        modules: [path.resolve(__dirname, './src'), './node_modules'],
        alias: {
            "@": path.resolve(__dirname, "./src/"),
        },
    },
    module: {
        rules: [
            {
                test: /\.(js|jsx)$/,
                exclude: /node_module/,
                use:{
                    loader: 'babel-loader'
                }
            },
            {
                test: /\.ts$/,
                exclude: /node_module/,
                use: {
                    loader: "ts-loader",
                },
            },
            {
                test: /\.less$/i,
                use: [
                    // compiles Less to CSS
                    "style-loader",
                    "css-loader",
                    "less-loader",
                ],
            },
            {
                test: /\.css$/i,
                use: ["style-loader", "css-loader"],
            },
        ]
    },
    plugins: [
        new HtmlWebpackPlugin({
            template: path.join(__dirname, './src/dev_server/index.html'),
        }),
    ]
}
