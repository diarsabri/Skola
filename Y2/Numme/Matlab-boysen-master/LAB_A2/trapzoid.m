
function y = trapzoid(h,z)

n=(1-0)/h;

tv=0;
for i=0:n-1
   
  s = h*(trap(i*h,z)/2+trap(i*h+h,z)/2);
  tv= tv + s;
  
end

y = tv;

end