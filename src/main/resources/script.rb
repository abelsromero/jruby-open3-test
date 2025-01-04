require 'open3'

# out, err, status = Open3.capture3("make")
# puts "Out: #{out}"
# puts "Err: #{err}"
#
# out, err, status = Open3.capture3('cat', '/home/asalgadr/github/asciidoctor-maven-examples/asciidoctor-pdf-example/target/generated-docs/example-manual.pdf')
# puts "Out: #{out}"
# puts "Err: #{err}"

out, err, status = Open3.capture3('ls')
puts "* Std Output:\n#{out}"
puts "* Error Output:\n#{err}"